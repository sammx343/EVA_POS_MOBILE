package com.example.smayor.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SellPanel extends FragmentActivity implements ButtonsFragment.OnFragmentInteractionListener, NotPayScreen.OnFragmentInteractionListener
        , TirillaDePagoFragment.OnFragmentInteractionListener, NumberPadFragment.OnFragmentInteractionListener {
    TextView articleCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_panel);
        articleCode = (TextView) findViewById(R.id.articleCode);

        ButtonsFragment buttonsFragment = new ButtonsFragment();
        addDynamicFragment(buttonsFragment, R.id.fragment_container);

        NumberPadFragment numberPadFragment = new NumberPadFragment();
        addDynamicFragment(numberPadFragment, R.id.numberPadFragment);
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

    public void removeFragment(Fragment myFrag){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_in_down);
        transaction.remove(myFrag);
        transaction.commit();
        manager.popBackStack();
    }

    public void addDynamicFragment(Fragment fragment, int fragmentId){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(fragmentId, fragment);

        transaction.commit();
    }

    public void replaceFragments(Fragment fragment, int idContainer){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_in_down);
        transaction.replace(idContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addTirilloDePagoFragment(View view) {
        TirillaDePagoFragment tirillaDePago = new TirillaDePagoFragment();
        replaceFragments(tirillaDePago, R.id.panel);
        //Toast.makeText(this, "Prueba", Toast.LENGTH_SHORT).show();
    }
}
