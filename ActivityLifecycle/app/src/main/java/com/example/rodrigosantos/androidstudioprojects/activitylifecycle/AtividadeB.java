package com.example.rodrigosantos.androidstudioprojects.activitylifecycle;
import com.example.rodrigosantos.androidstudioprojects.activitylifecycle.util.StatusTracker;
import com.example.rodrigosantos.androidstudioprojects.activitylifecycle.util.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;


/*
    Rodrigo Silva dos Santos
    RA: 816155559
 */


public class AtividadeB extends Activity {

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_b);
        mActivityName = getString(R.string.activity_b);
        mStatusView = (TextView)findViewById(R.id.status_view_b);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_b);
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
        mStatusTracker.clear();
    }

    public void startDialog(View v) {
        Intent intent = new Intent(AtividadeB.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityA(View v) {
        Intent intent = new Intent(AtividadeB.this, AtividadeA.class);
        startActivity(intent);
    }

    public void startActivityC(View v) {
        Intent intent = new Intent(AtividadeB.this, AtividadeC.class);
        startActivity(intent);
    }

    public void finishActivityB(View v) {
        AtividadeB.this.finish();
    }

}