package com.example.rodrigosantos.androidstudioprojects.pipoca;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    public static ArrayList<String> listarNomes(ArrayList<Filme> filmes){
        ArrayList<String> nomes = new ArrayList<>();
        for(Filme filme: filmes ){
            nomes.add(filme.getTitulo());
        }
        return nomes;
    }


    public static ArrayList<Filme> listarFilmes(String genero){
        Filme[] lista;
        ArrayList<Filme> filmes =  new ArrayList<>();
        for(Filme filme: todosFilmes()){
            if(filme.getGenero().equals(genero) || genero.equals("Todos")){
                filmes.add(filme);
            }
        }
        lista = filmes.toArray(new Filme[0]);

        Arrays.sort(lista);
        filmes = new ArrayList<>();
        for(int i = 0;i<lista.length;i++){
            filmes.add(lista[i]);
        }
        return  filmes;
    }

    private static ArrayList<Filme> todosFilmes(){
        ArrayList<Filme> filmes = new ArrayList<>();
        Filme filme;
        filme = new Filme();
        filme.setTitulo("Noite do Terror");
        filme.setDataLancamento("11/03/2018");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Rodrigo Santos");
        filme.setPopularidade(80.0);
        filme.setGenero("Horror");
        filme.setPosterPath("imagem");
        filmes.add(filme);

        filme=new Filme();
        filme.setTitulo("Filme engraçado");
        filme.setDataLancamento("11/03/2018");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Outro diretor");
        filme.setPopularidade(80.0);
        filme.setGenero("Comédia");
        filme.setPosterPath("imagem");
        filmes.add(filme);

        filme=new Filme();
        filme.setTitulo("Mazze runner");
        filme.setDataLancamento("11/03/2018");
        filme.setDescricao("alguma coisa");
        filme.setDiretor("Outro diretor");
        filme.setPopularidade(90.0);
        filme.setGenero("Ação");
        filme.setPosterPath("imagem");
        filmes.add(filme);


        filme=new Filme();
        filme.setTitulo("Filme Dramatico");
        filme.setDataLancamento("11/03/2018");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Diretor drama");
        filme.setPopularidade(50.0);
        filme.setGenero("Drama");
        filme.setPosterPath("imagem");
        filmes.add(filme);

        filme=new Filme();
        filme.setTitulo("Atividade Paranormal");
        filme.setDataLancamento("11/03/2012");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Rodrigo Santos");
        filme.setPopularidade(80.0);
        filme.setGenero("Suspense");
        filme.setPosterPath("imagem");
        filmes.add(filme);

        filme=new Filme();
        filme.setTitulo("Inatividade Paranormal");
        filme.setDataLancamento("11/03/2012");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Gasparzinho");
        filme.setPopularidade(80.0);
        filme.setGenero("Suspense");
        filme.setPosterPath("imagem");
        filmes.add(filme);

        filme=new Filme();
        filme.setTitulo("Onde as avez voão");
        filme.setDataLancamento("02/03/2012");
        filme.setDescricao("alguma coisa para descrever o filme");
        filme.setDiretor("Richard");
        filme.setPopularidade(80.0);
        filme.setGenero("Documentario");
        filme.setPosterPath("imagem");
        filmes.add(filme);


        return filmes;
    }


}
