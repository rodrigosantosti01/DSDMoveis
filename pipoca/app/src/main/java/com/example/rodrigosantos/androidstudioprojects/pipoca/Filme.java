package com.example.rodrigosantos.androidstudioprojects.pipoca;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.Collator;

public class Filme implements Serializable, Comparable {

    private int id;
    private String titulo;
    private String descricao;
    private double popularidade;
    private String dataLancamento;
    private String posterPath;
    private String diretor;
    private String genero;


    public String getGenero() { return genero; }

    public void setGenero(String genero) {this.genero = genero; }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPopularidade() {
        return popularidade;
    }
    public void setPopularidade(double popularidade) {
        this.popularidade = popularidade;
    }


    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", popularidade=" + popularidade +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", diretor='" + diretor + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return 0;
        } else {

            Filme filme = (Filme) o;

            Collator c = Collator.getInstance();

            c.setStrength(Collator.PRIMARY);
            return c.compare(this.titulo, filme.getTitulo());
        }
    }
}
