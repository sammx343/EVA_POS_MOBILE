package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Classes.TypeWritter;

public class SplashScreen extends AppCompatActivity {
    TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loading = (TextView) findViewById(R.id.loading);

        startTimerThread();
    }

    private void startTimerThread() {
        Thread th = new Thread(new Runnable() {
            int ticks = 0;
            public void run() {
                while (ticks < 3) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ticks++;
                            TypeWritter tc = new TypeWritter();
                            tc.animateText("...", 300, loading, "");

                            if(ticks>2){
                                Intent intent;
                                intent = new Intent(getApplicationContext(), LoadDevices.class);
                                startActivity(intent);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }
}
