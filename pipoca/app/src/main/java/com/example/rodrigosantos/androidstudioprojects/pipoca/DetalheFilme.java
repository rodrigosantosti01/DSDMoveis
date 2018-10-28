package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DetalheFilme extends AppCompatActivity {
    private TextView titulo;
    private TextView direcaoDetailTextView;
    private TextView popularidade;
    private TextView dataLancamento;
    private TextView descricao;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        titulo = (TextView) findViewById(R.id.titulo);
        direcaoDetailTextView =(TextView) findViewById(R.id.direcaoDetailTextView);
        popularidade = (TextView) findViewById(R.id.popularidadeText);
        dataLancamento = (TextView) findViewById(R.id.dataLancamento);
        descricao = (TextView) findViewById(R.id.descricao);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();

        Filme filme = (Filme)intent.getSerializableExtra(ListaFilmesActivity.FILME);

        titulo.setText(filme.getTitulo());
        direcaoDetailTextView.setText((filme.getDiretor()));
        popularidade.setText(Double.toString(filme.getPopularidade()));
        dataLancamento.setText(filme.getDataFormatada());
        descricao.setText(filme.getDescricao());

        WebServiceGetDirector director = new WebServiceGetDirector();
        director.execute(filme.getId());

        ImageGetter imageGetter = new ImageGetter();
        Log.i(" imagem ",filme.getPosterPath());
        imageGetter.execute(filme.getPosterPath());
    }


    private class ImageGetter extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urlS) {
            try {
                URL url = new URL(getApplicationContext().getString(R.string.uri_image) + urlS[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream= connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                System.out.println(figura);
                return figura;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        @Override
        protected void onPostExecute(Bitmap figura) {
            imageView.setImageBitmap(figura);
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
                        direcaoDetailTextView.setText(responseResults.get(contator).getName());
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
