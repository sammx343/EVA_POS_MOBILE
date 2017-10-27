package com.example.smayor.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ButtonsFragment.OnFragmentInteractionListener, NotPayScreen.OnFragmentInteractionListener{
    TextView articleCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleCode = (TextView) findViewById(R.id.articleCode);

        ButtonsFragment buttonsFragment = new ButtonsFragment();
        addDynamicFragment(buttonsFragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void addDynamicFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragment_container, fragment);

        transaction.commit();
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

    public void replaceFragments(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
