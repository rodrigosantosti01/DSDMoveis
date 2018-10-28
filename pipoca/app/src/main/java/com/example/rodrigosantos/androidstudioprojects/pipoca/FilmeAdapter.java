package com.example.rodrigosantos.androidstudioprojects.pipoca;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        }
        else{
            raiz = convertView;
            viewHolder = (ViewHolder) raiz.getTag();
        }
        viewHolder.filmeId.setText("ID:"+toString().valueOf(filme.getId()));
        viewHolder.titulo.setText(filme.getTitulo());
        viewHolder.diretor.setText(filme.getDiretor());
        viewHolder.dataLancamento.setText(filme.getDataFormatada());
        return  raiz;
    }
}
