package com.example.rodrigosantos.androidstudioprojects.activitylifecycle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AtividadeB extends AppCompatActivity {

    private EditText atividadeB;
    private EditText statusAtividadeB;
    private String dadosatividadeA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_b);
        Log.i("ciclodevida", "MainActivity:onCreate");

        dadosatividadeA = getIntent().getStringExtra("atividadesA");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ciclodevida", "MainActivity:onStart");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ciclodevida", "MainActivity:onResume");
        String status = ("Atividade B: Resumed \n Atividade A:Paused");
        statusAtividadeB = (EditText) findViewById(R.id.statusAtividadeB);
        statusAtividadeB.setText(status);

        String mensagem = (" B.onResume()\n B.onStart()\n B.onCreate");
        atividadeB = (EditText) findViewById(R.id.atividadeB);
        atividadeB.setText(dadosatividadeA + mensagem);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ciclodevida", "MainActivity:onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ciclodevida", "MainActivity:onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ciclodevida", "MainActivity:onDestroy");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("ciclodevida", "MainActivity:onRestart");
    }


    public void iniciarAtividade_BC(View view) {
        Log.i("ciclodevida", "MainActivity:entrou nesse metodo");

//      String dadosAtividades = dadosAtividade.getEditableText().toString();
        Intent intent = new Intent(this, AtividadeC.class);
//      intent.putExtra("atividadesA", dadosAtividades);
        startActivity(intent);
    }
}