
package com.example.rodrigosantos.androidstudioprojects.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


/*
    Rodrigo Silva dos Santos
    RA: 816155559
 */


public class DialogActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }

    public void finishDialog(View v) {
        DialogActivity.this.finish();
    }
}
