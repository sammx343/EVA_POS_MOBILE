package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void sell(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), SellPanel.class);
        startActivity(intent);
    }

    public void changeView(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
