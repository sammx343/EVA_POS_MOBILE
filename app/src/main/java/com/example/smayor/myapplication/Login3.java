package com.example.smayor.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Login3 extends AppCompatActivity {
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = (TextView) findViewById(R.id.username);
    }

    public void login(View view) {
        if(username.getText().toString().length()<1){
            Snackbar mSnackBar = Snackbar.make(view, "TOP SNACKBAR", Snackbar.LENGTH_LONG);
            view = mSnackBar.getView();
            FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.RED);
            TextView mainTextView = (TextView) (view).findViewById(android.support.design.R.id.snackbar_text);
            mainTextView.setTextColor(Color.WHITE);
            mSnackBar.show();
        }
        else{
            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void exit2(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}
