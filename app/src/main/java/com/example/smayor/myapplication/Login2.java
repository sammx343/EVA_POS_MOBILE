package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
    }

    public void login(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void exit2(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), Login3.class);
        startActivity(intent);
    }
}
