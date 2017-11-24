package com.example.smayor.myapplication;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import Classes.XMLButtonParser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ButtonsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ButtonsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ButtonsFragment() {
        // Required empty public constructor
    }
    int rows;
    int columns;
    LinearLayout cancelButton;
    Button noSell;
    ArrayList<Classes.Button> buttons;
    ArrayList<Classes.Button> functionButtons;
    ArrayList<Classes.Button> payButtons;
    int buttonIndex = 0;
    int buttonPayIndex = 0;
    int buttonFunctionIndex = 0;
    String tecladoState = "principal"; //Estado del teclado de la botonera, indica si estamos en principal, funciones, pago, etc
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        try {
            XmlPullParser parser = getResources().getXml(R.xml.teclado);
            XMLButtonParser buttonParser = new XMLButtonParser();
            buttons = buttonParser.getXMLfromResource(parser,"principal");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        try {
            XmlPullParser parser = getResources().getXml(R.xml.teclado);
            XMLButtonParser buttonParser = new XMLButtonParser();
            functionButtons = buttonParser.getXMLfromResource(parser,"funciones");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        try {
            XmlPullParser parser = getResources().getXml(R.xml.teclado);
            XMLButtonParser buttonParser = new XMLButtonParser();
            payButtons = buttonParser.getXMLfromResource(parser,"tecladoPagos");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //Código para crear categorias basadas en la botonera principal
        /*for (Classes.Button button :
                buttons) {
            if(button.getCategorias().equals("Categorias") && button.getLabel() != null) {
                categoryButtons.add(button);
            }
        }*/

        rootView = inflater.inflate(R.layout.fragment_buttons, container, false);
        rootView = rootView();

        //////////ROOT VIEW

        /*cancelButton = (LinearLayout) rootView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " Cancel ", Toast.LENGTH_LONG).show();
            }
        });
        noSell = (Button) rootView.findViewById(R.id.noSell);
        noSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotPayScreen payScreen = new NotPayScreen();
                ((SellPanel) getActivity()).replaceFragments(payScreen, R.id.fragment_container, "NO_SELL");
            }
        });*/
        return rootView;
    }

    //Primer LinearLayout, Vertical, que contienen N Filas
    public LinearLayout rootView(){
        buttonIndex = 0;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        if(preferences.getString("Rows", "").equals(""))
            rows = 8;
        else
            rows = Integer.parseInt(preferences.getString("Rows", "").toString());


        if(preferences.getString("Columns", "").equals(""))
            columns = 8;
        else
            columns = Integer.parseInt(preferences.getString("Columns", "").toString());


        LinearLayout lrl = new LinearLayout(getContext());
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        rlp.setMargins(0,5,0,0);
        lrl.setLayoutParams(rlp);
        lrl.setOrientation(LinearLayout.VERTICAL);
        lrl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        lrl.setWeightSum(rows);

        for(int index = 0; index < rows; index++){
            lrl.addView(createLinearLayout());
        }
        return lrl;
    }

    //Método que crea M LinearLayout (Rows o  filas), que contienen los botones de cada fila
    public LinearLayout createLinearLayout(){
        LinearLayout layout2 = new LinearLayout(getContext());
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,1);
        rlp.setMargins(0,0,3,0);
        layout2.setLayoutParams(rlp);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setWeightSum(columns);
        for(int index = 0; index < columns; index++){
            layout2.addView(createButton());
        }
        return layout2;
    }

    //Método que crea un "botón", (en realidad es otro LinearLayout) en cada columna
    public LinearLayout createButton(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,  1);
        params.setMargins(0,3,3,0);
        //params.setMarginEnd(1);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setWeightSum(2);
        layout.setOrientation(LinearLayout.VERTICAL);

        ImageView buttonImage = new ImageView(getContext());
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        imageViewParams.setMargins(0,5,0,0);
        buttonImage.setLayoutParams(imageViewParams);

        TextView buttonTitle = new TextView(getContext());

        //Crea los botones de la botonera con ID principal
        final Classes.Button button;
        if(tecladoState.equals("principal")){
            button = principalButtonsMenu(buttonTitle, buttonImage);
        }
        else if(tecladoState.equals("tecladoPagos")){
            button = createPaymentButtons(buttonTitle);
        }
        else{
            button = new Classes.Button(0, "", "", "","", "","", "");
        }

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        buttonTitle.setLayoutParams(textViewParams);
        buttonTitle.setGravity(Gravity.CENTER);

        layout.setLayoutParams(params);
        layout.addView(buttonImage);
        layout.addView(buttonTitle);
        layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        createFunctionForButton(layout, button.getLabel());
        return layout;
    }

    public void createFunctionForButton(LinearLayout layout, final String label){
        if(label.equals("Pagar")){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tecladoState = "tecladoPagos";
                    rootView = rootView();
                }
            });
        }else if(label.equals("Cancelar")){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), label, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //Botones de la botonera principal, se configuran a partir de los id de teclado principal y funciones
    private Classes.Button principalButtonsMenu(TextView buttonTitle, ImageView buttonImage){
        Classes.Button button;
        if(buttonIndex < (rows*(columns-1))) {
            button = buttons.get(buttonIndex);
            createPrincipalButtons(buttonTitle, button, buttonImage);
        }else if(buttonFunctionIndex < functionButtons.size()) {
            button = functionButtons.get(buttonFunctionIndex);
            createFuncionesButtons(buttonTitle, button);
        }else{
            button = new Classes.Button(0, "", "", "","", "","", "");
        }
        return button;
    }

    private void createPrincipalButtons(TextView buttonTitle, Classes.Button button, ImageView buttonImage){
        if(buttonIndex < buttons.size()-1){
            buttonTitle.setText(button.getLabel());

            String mDrawableName = "p" + button.getImagen();

            if(button.getCategorias().equals("Categorias") && button.getLabel() != null)
                mDrawableName = capitalCaseToSnakeCase(button.getImagen());

            int resID = getResources().getIdentifier(mDrawableName , "drawable", getContext().getPackageName());

            buttonImage.setImageResource(resID);
        }
        buttonIndex++;
    }

    private void createFuncionesButtons(TextView buttonTitle, Classes.Button button){
        if(buttonFunctionIndex < functionButtons.size()){
            System.out.println("Es una propiedad" + button.getLabel());
            buttonTitle.setText(button.getLabel());
        }
        buttonFunctionIndex++;
    }

    private  Classes.Button createPaymentButtons(TextView buttonTitle){
        Classes.Button button;
        if(buttonFunctionIndex>(rows*columns-1)-1  && buttonPayIndex < payButtons.size()){
            button = payButtons.get(buttonPayIndex);
            buttonTitle.setText(button.getLabel());
            buttonPayIndex = 0;
        }else{
            button = new Classes.Button(0, "", "", "","", "","", "");
        }
        buttonFunctionIndex++;
        return button;
    }

    //Convertir un nombre de variable de Capitalize o camelCase a snake_case
    public String capitalCaseToSnakeCase(String text) {
        String textArray[] = text.split("(?=\\p{Upper})");
        String capitalcaseToSnakeCase = "";
        for (String word: textArray) {
            if(!word.equals(""))
                capitalcaseToSnakeCase += "_" + word.toLowerCase();
        }
        capitalcaseToSnakeCase = "categorias" + capitalcaseToSnakeCase;
        return capitalcaseToSnakeCase;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
