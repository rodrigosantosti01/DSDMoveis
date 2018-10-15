package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheFilme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        TextView textView = (TextView) findViewById(R.id.txtFilme);
        Intent intent = getIntent();
        Filme filme = (Filme)intent.getSerializableExtra(ListaFilmesActivity.FILME);
        textView.setText(filme.toString());
    }
}
