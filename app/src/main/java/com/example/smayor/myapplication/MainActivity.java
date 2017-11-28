package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int[] layouts = {R.layout.activity_main, R.layout.activity_main2, R.layout.activity_main3, R.layout.activity_main4};
    int layoutIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sell(View view){
        Intent intent;
        intent = new Intent(getApplicationContext(), SellPanel.class);
        startActivity(intent);
    }

    public void changeView(View view){
        if(layoutIndex == layouts.length)
            layoutIndex = 0;
        setContentView(layouts[layoutIndex]);
        layoutIndex++;
    }

    public void configuration(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), ConfigurationScreen.class);
        startActivity(intent);
    }
}