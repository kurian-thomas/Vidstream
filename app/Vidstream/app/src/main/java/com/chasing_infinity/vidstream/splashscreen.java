package com.chasing_infinity.vidstream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread load = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        load.start();
    }
}
