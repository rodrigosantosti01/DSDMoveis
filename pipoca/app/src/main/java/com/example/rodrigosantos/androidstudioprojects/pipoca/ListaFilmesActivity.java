package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFilmesActivity extends AppCompatActivity {
    public static final String FILME = "filme";
    ArrayList<Filme> filmes;
    ArrayList<String> nomes;
    Activity atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        atividade = this;
        Intent intent = getIntent();
        String genero = intent.getStringExtra(MainActivity.CHAVE_GENERO);
        filmes = Data.listarFilmes(genero);
        nomes = Data.listarNomes(filmes);

        ListView listView = (ListView) findViewById(R.id.lista_filmes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,nomes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Intent intent = new Intent(atividade,DetalheFilme.class);
                intent.putExtra(FILME,filmes.get(position));
                startActivity(intent);
            }
        });
    }
}
