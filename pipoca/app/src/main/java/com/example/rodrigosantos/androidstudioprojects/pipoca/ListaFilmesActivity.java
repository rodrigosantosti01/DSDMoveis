package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {
    public static final String FILME = "filme";
    List<Filme> filmesList;
    private FilmeAdapter adapter;
    private ListView filmeListView;
    Activity atividade;
    String diretor="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        atividade = this;
        Intent intent = getIntent();
        String genero = intent.getStringExtra(MainActivity.CHAVE_GENERO);

        filmesList = new ArrayList<>();
        filmeListView = findViewById(R.id.filmesListView);

        WebServiceClient filme = new WebServiceClient();
        filme.execute(genero);


        filmeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Intent intent = new Intent(atividade,DetalheFilme.class);
                intent.putExtra(FILME,filmesList.get(position));
                startActivity(intent);
            }
        });
    }

        private class WebServiceClient extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... genero) {
            try {

                URL url = createURL(genero[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String linha = null;
                StringBuilder stringBuilder = new StringBuilder("");
                while ((linha = reader.readLine()) != null) {
                    stringBuilder.append(linha);
                }
                String json = stringBuilder.toString();
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute (String json){
            try {

                Gson gson = new Gson();
                FilmePonte filmesponte = gson.fromJson(json,FilmePonte.class);

                List<ResponseResult> responseResults = filmesponte.getResults();
                for (ResponseResult r: responseResults){
                    System.out.println(r.toString());

                    ListaFilmesActivity.WebServiceGetDirector director = new ListaFilmesActivity.WebServiceGetDirector();
                    director.execute(r.getId());

                    Filme filme = new Filme(
                            r.getId(),
                            r.getTitle(),
                            r.getOverview(),
                            r.getPopularity(),
                            r.getRelease_date(),
                            r.getPoster_path(),
                            diretor);
                    filmesList.add(filme);
                }

                adapter = new FilmeAdapter(getApplicationContext(),filmesList);
                filmeListView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private URL createURL(String genero){
        String apiKey = getString(R.string.api_key);
        String baseURL = getString(R.string.web_service_filmes);

        try{
            String urlString = baseURL + apiKey +"&with_genres=" + URLEncoder.encode(genero,"UTF-8");
            return new URL(urlString);

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private class WebServiceGetDirector extends AsyncTask<Integer,Void,String> {

        @Override
        protected String doInBackground(Integer... idFilme) {
            try {
                String uri = getApplicationContext().getString(R.string.uri_director, idFilme[0]+"");
                URL url = new URL(uri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String linha = null;
                StringBuilder stringBuilder = new StringBuilder("");
                while ((linha = reader.readLine()) != null) {
                    stringBuilder.append(linha);
                }
                String json = stringBuilder.toString();
                System.out.println(json);
                return json;
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            try {

                Gson gson = new Gson();
                ResponseCreditos responseCredits = gson.fromJson(json, ResponseCreditos.class);
                List<ResponseCrew> responseResults = responseCredits.getCrew();
                Boolean flag = true;
                Integer contator = 0;
                while (flag){
                    if(responseResults.get(contator).getDepartment().equals("Directing")){
                        diretor = responseResults.get(contator).getName();
                        flag = false;
                    }
                    contator++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
