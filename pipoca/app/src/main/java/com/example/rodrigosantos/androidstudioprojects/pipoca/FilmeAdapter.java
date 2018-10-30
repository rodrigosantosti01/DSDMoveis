package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class FilmeAdapter  extends ArrayAdapter<Filme>{
    public FilmeAdapter(Context context, List<Filme> cast ){
        super(context,-1,cast);
    }
    private  static class ViewHolder{
        ImageView filmeImage;
        TextView filmeId;
        TextView titulo;
        TextView diretor;
        TextView dataLancamento;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent){
        Filme filme = getItem(position);
        View raiz = null;
        ViewHolder viewHolder = null;
        Context context = getContext();
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            raiz = inflater.inflate(R.layout.list_item,parent,false);
            viewHolder = new ViewHolder();
            raiz.setTag(viewHolder);
            viewHolder.filmeId  = raiz.findViewById((R.id.filmeId));
            viewHolder.filmeImage = raiz.findViewById(R.id.imageViewFilme);
            viewHolder.titulo = raiz.findViewById(R.id.tituloFilme);
            viewHolder.diretor = raiz.findViewById(R.id.diretorFilme);
            viewHolder.dataLancamento = raiz.findViewById(R.id.dataLancamentoFilme);

            WebServiceGetDirector director = new WebServiceGetDirector(viewHolder.diretor);
            director.execute(filme.getId());
        }
        else{
            raiz = convertView;
            viewHolder = (ViewHolder) raiz.getTag();
        }
        viewHolder.filmeId.setText("ID:"+toString().valueOf(filme.getId()));
        viewHolder.titulo.setText(filme.getTitulo());
        viewHolder.dataLancamento.setText(filme.getDataFormatada());
        return  raiz;
    }
    private class WebServiceGetDirector extends AsyncTask<Integer,Void,String> {
        private TextView diretor;

        WebServiceGetDirector(TextView diretor){
            this.diretor = diretor;
        }

        @Override
        protected String doInBackground(Integer... idFilme) {
            try {
                String uri = getContext().getString(R.string.uri_director, idFilme[0]+"");
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
                while (flag==true && contator<responseResults.size()){
                    if(responseResults.get(contator).getDepartment().equals("Directing")){
                        diretor.setText(responseResults.get(contator).getName());
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
