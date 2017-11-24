package com.example.smayor.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import Classes.XMLButtonParser;

public class SellPanel extends FragmentActivity implements ButtonsFragment.OnFragmentInteractionListener, NotPayScreen.OnFragmentInteractionListener
        , TirillaDePagoFragment.OnFragmentInteractionListener, NumberPadFragment.OnFragmentInteractionListener, OperatorViewFragment.OnFragmentInteractionListener,
        NumberPadFragment.PayChange{

    ButtonsFragment buttonsFragment;
    NumberPadFragment numberPadFragment;
    OperatorViewFragment operatorView;
    private float y1,y2;
    static final int MIN_DISTANCE = 150;
    private static final String ns = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String orientation = preferences.getString("Orientation", "");

        if(orientation.equals("1"))
            setContentView(R.layout.activity_sell_panel);
        else
            setContentView(R.layout.activity_sell_panel2);

        buttonsFragment = new ButtonsFragment();
        addDynamicFragment(buttonsFragment, R.id.fragment_container);

        numberPadFragment = new NumberPadFragment();
        addDynamicFragment(numberPadFragment, R.id.numberPadFragment);

        operatorView = new OperatorViewFragment();
        addDynamicFragment(operatorView, R.id.operatorView);

        try {
            XmlPullParser parser = getResources().getXml(R.xml.teclado);
            XMLButtonParser buttonParser = new XMLButtonParser();
            buttonParser.getXMLfromResource(parser,"ajuste");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, getScreenDensity() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void getGesture(MotionEvent event){
        boolean tirillaDePagoShowing = fragmentIsShowing("TIRILLA_DE_PAGO");
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) > MIN_DISTANCE){
                    if (y2 > y1 && !tirillaDePagoShowing){
                        TirillaDePagoFragment tirillaDePago = new TirillaDePagoFragment();
                        replaceFragments(tirillaDePago, R.id.panel, "TIRILLA_DE_PAGO");
                    }
                    else if(y1 > y2 && tirillaDePagoShowing){
                        Fragment frag = getFragmentManager().findFragmentByTag("TIRILLA_DE_PAGO");
                        removeFragment(frag);
                    }
                }
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getGesture(event);
        return super.dispatchTouchEvent(event);
    }

    //Muestro para confirmar si un fragment se está mostrando
    public boolean fragmentIsShowing(String fragmentTag){
        TirillaDePagoFragment myFragment = (TirillaDePagoFragment)getFragmentManager().findFragmentByTag(fragmentTag);
        return (myFragment != null && myFragment.isVisible());
    }

    //Métodos de botones del NumberPad
    public void onButtonClicked(View view) {
        Button b = (Button) view;
        String newText = b.getText().toString();
        sendText(newText);
    }

    public double getScreenDensity(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        return(Math.sqrt(x+y));
    }

    public void deleteCharacter(View view) {
        deleteCharacter();
    }

    //Métodos de la interfaz de NumberPadFragment = Teclado Númerico
    public void sendText(String text){
        operatorView.updateText(text);
    }

    public void deleteCharacter(){
        operatorView.deleteCharacter();
    }

    //Métodos para agregar, reemplazar o remover Fragment
    public void removeFragment(Fragment myFrag){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_from_out_bottom, R.animator.slide_in_bottom);
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

    public void replaceFragments(Fragment fragment, int idContainer, String fragmentTag){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_in_down);
        transaction.replace(idContainer, fragment, fragmentTag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Método del botón para mostrar la tirilla de pago
    public void addTirilloDePagoFragment() {
        if(!fragmentIsShowing("TIRILLA_DE_PAGO")){
            TirillaDePagoFragment tirillaDePago = new TirillaDePagoFragment();
            replaceFragments(tirillaDePago, R.id.panel, "TIRILLA_DE_PAGO");
        }
        //Toast.makeText(this, "Prueba", Toast.LENGTH_SHORT).show();
    }
}
