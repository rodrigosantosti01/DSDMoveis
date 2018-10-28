package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerGeneros;
    String genero = "todos";
    Integer generoId;
    List<Genero> genres;

    public static final String CHAVE_GENERO = "textGenero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        genres = new ArrayList<>();
        genres = todosGeneros();
        spinnerGeneros = (Spinner)findViewById(R.id.generos);
        spinnerGeneros.setOnItemSelectedListener(new FilmeSelecionado());
    }

    public void listarFilmes(View view){
        Intent intent = new Intent(this,ListaFilmesActivity.class);
        intent.putExtra(CHAVE_GENERO, generoId.toString());
        startActivity(intent);
    }

    private class FilmeSelecionado implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
            genero = (String) parent.getItemAtPosition(position);
            for(int i = 0; i<genres.size();i++){
                if(genero.equals(genres.get(i).getName())){
                    generoId = genres.get(i).getId();
                }
            }
        }
        public void onNothingSelected(AdapterView<?> parent){
        }
    }
        private static ArrayList<Genero> todosGeneros(){
            ArrayList<Genero> generos = new ArrayList<>();
            Genero genero;
            genero = new Genero();
            genero.setId(28);
            genero.setName("Ação");
            generos.add(genero);

            genero = new Genero();
            genero.setId(12);
            genero.setName("Aventura");
            generos.add(genero);

            genero = new Genero();
            genero.setId(16);
            genero.setName("Animação");
            generos.add(genero);

            genero = new Genero();
            genero.setId(35);
            genero.setName("Comédia");
            generos.add(genero);

            genero = new Genero();
            genero.setId(80);
            genero.setName("Crime");
            generos.add(genero);

            genero = new Genero();
            genero.setId(18);
            genero.setName("Drama");
            generos.add(genero);

            genero = new Genero();
            genero.setId(99);
            genero.setName("Documentário");
            generos.add(genero);

            genero = new Genero();
            genero.setId(10751);
            genero.setName("Família");
            generos.add(genero);

            genero = new Genero();
            genero.setId(14);
            genero.setName("Fantasia");
            generos.add(genero);

            genero = new Genero();
            genero.setId(36);
            genero.setName("História");
            generos.add(genero);

            genero = new Genero();
            genero.setId(27);
            genero.setName("Horror");
            generos.add(genero);

            genero = new Genero();
            genero.setId(10402);
            genero.setName("Musical");
            generos.add(genero);

            genero = new Genero();
            genero.setId(9648);
            genero.setName("Mistério");
            generos.add(genero);

            genero = new Genero();
            genero.setId(10749);
            genero.setName("Romance");
            generos.add(genero);

            genero = new Genero();
            genero.setId(878);
            genero.setName("Ficção Científica");
            generos.add(genero);

            genero = new Genero();
            genero.setId(10770);
            genero.setName("Filme para TV");
            generos.add(genero);

            genero = new Genero();
            genero.setId(53);
            genero.setName("Suspense");
            generos.add(genero);

            genero = new Genero();
            genero.setId(10756);
            genero.setName("Guerra");
            generos.add(genero);

            genero = new Genero();
            genero.setId(37);
            genero.setName("Western");
            generos.add(genero);

            return generos;
        }
    }
