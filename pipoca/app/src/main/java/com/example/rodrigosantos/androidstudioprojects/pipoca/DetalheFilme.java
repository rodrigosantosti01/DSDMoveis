package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheFilme extends AppCompatActivity {
    private TextView titulo;
    private TextView direcaoDetailTextView;
    private TextView popularidade;
    private TextView dataLancamento;
    private TextView descricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        titulo = (TextView) findViewById(R.id.titulo);
        direcaoDetailTextView =(TextView) findViewById(R.id.direcaoDetailTextView);
        popularidade = (TextView) findViewById(R.id.popularidadeText);
        dataLancamento = (TextView) findViewById(R.id.dataLancamento);
        descricao = (TextView) findViewById(R.id.descricao);
        Intent intent = getIntent();
        Filme filme = (Filme)intent.getSerializableExtra(ListaFilmesActivity.FILME);
        titulo.setText(filme.getTitulo());
        direcaoDetailTextView.setText((filme.getDiretor()));
        popularidade.setText(Double.toString(filme.getPopularidade()));
        dataLancamento.setText(filme.getDataLancamento());
        descricao.setText(filme.getDescricao());
    }
}
