package com.example.smayor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView username;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, getResources().getConfiguration().orientation+"", Toast.LENGTH_SHORT).show();
        username = (TextView) findViewById(R.id.username);
        message = (TextView) findViewById(R.id.message);
    }

    public void login(View view) {
        if(username.getText().toString().length()<1){
            Toast.makeText(this, " Error de Ingreso ", Toast.LENGTH_SHORT).show();
            message.setText("Error de Inicio de Sesión");
        }
        else{
            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void exit2(View view) {
        message.setText("");
        Intent intent;
        intent = new Intent(getApplicationContext(), Login2.class);
        startActivity(intent);
    }
}
