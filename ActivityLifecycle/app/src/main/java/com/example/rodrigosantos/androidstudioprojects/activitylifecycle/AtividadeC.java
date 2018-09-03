package com.example.rodrigosantos.androidstudioprojects.activitylifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class AtividadeC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_c);
        Log.i("ciclodevida","AtividadeC:onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ciclodevida","AtividadeC:onStart");



    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ciclodevida","AtividadeC:onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ciclodevida","AtividadeC:onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ciclodevida","AtividadeC:onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ciclodevida","AtividadeC:onDestroy");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("ciclodevida","AtividadeC:onRestart");
    }

}
