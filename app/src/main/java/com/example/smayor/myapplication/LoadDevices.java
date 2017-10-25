package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import Classes.TypeWritter;

public class LoadDevices extends AppCompatActivity {
    TextView loadingText;
    TextView telemetry;
    TextView bd;
    TextView validation;
    TextView customK3;
    TextView dataPhone;
    TextView scanner;
    TextView keyboard;
    boolean devicesReady = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_devices);

        loadingText = (TextView) findViewById(R.id.loadingText);
        telemetry = (TextView) findViewById(R.id.telemetryStatus);
        bd = (TextView) findViewById(R.id.bdStatus);
        validation = (TextView) findViewById(R.id.validationStatus);
        customK3 = (TextView) findViewById(R.id.customK3Status);
        dataPhone = (TextView) findViewById(R.id.dataPhoneStatus);
        scanner = (TextView) findViewById(R.id.scannerStatus);
        keyboard = (TextView) findViewById(R.id.keyboardStatus);

        loadDevices(new TextView[]{loadingText, telemetry, bd, validation, customK3, dataPhone, scanner, keyboard});

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollTouch);

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(devicesReady) {
                    Intent intent;
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(devicesReady) {
                    Intent intent;
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private void loadDevices(final TextView[] textViews) {
        for (TextView textView: textViews) {
            changeDeviceText(textView);
        }

        Thread th = new Thread(new Runnable() {
            public void run() {
                while (!devicesReady) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TypeWritter tc = new TypeWritter();
                            tc.animateText(" ...", 300, loadingText, "*Espere mientras se cargan los dispositivos");

                            //Si un solo dispositivo no está listo, [-], devicesReady se vuelve falso
                            devicesReady = true;
                            for (TextView textView: textViews) {
                                String evalText = (String) textView.getText();
                                if(evalText.contains("-")){
                                    devicesReady = false;
                                }
                            }

                            //Si devicesReady se vuelve falso, es porque hay al menos uno que no está listo
                            if(devicesReady){
                                tc.write = false;
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                loadingText.setText("Toque la pantalla para continuar");
                                                Toast.makeText(getApplicationContext(), " listos ", Toast.LENGTH_LONG).show();
                                            }
                                        }, 1000);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1200);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }

    public void changeDeviceText(final TextView textView){
        int Result = randomNumber(4000, 10000);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        String text = (String) textView.getText();
                        String setter = (randomNumber(1,3) == 1)? "OK" : "X";
                        text = text.replace("-", setter);
                        textView.setText(text);
                    }
                }, Result);
    }

    public int randomNumber(int Low, int High){
        Random r = new Random();
        return (r.nextInt(High-Low) + Low);
    }

    public void onBackPressed() {
        //super.onBackPressed();
    }
}
