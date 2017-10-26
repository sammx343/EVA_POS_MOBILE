package com.example.smayor.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ButtonsFragment.OnFragmentInteractionListener{
    TextView articleCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleCode = (TextView) findViewById(R.id.articleCode);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void onButtonClicked(View view) {
        Button b = (Button) view;
        String newText = b.getText().toString();
        String oldText = articleCode.getText().toString();
        articleCode.setText(oldText+newText);
    }

    public void deleteCharacter(View view) {
        int articleCodeLength = articleCode.getText().length();
        if(articleCodeLength > 0) {
            String newText = articleCode.getText().toString().substring(0, articleCodeLength - 1);
            articleCode.setText(newText);
        }
    }
}
