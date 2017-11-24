package com.example.smayor.myapplication;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OperatorViewFragment extends Fragment {

    public OperatorViewFragment() {
        // Required empty public constructor
    }

    TextView articleCode;
    LinearLayout showTirillaDePago;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_operator_view, container, false);

        articleCode = (TextView) rootView.findViewById(R.id.articleCode);

        showTirillaDePago = (LinearLayout) rootView.findViewById(R.id.buttonShowTirillaDePago);
        showTirillaDePago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SellPanel) getActivity()).addTirilloDePagoFragment();
            }
        });
        return rootView;
    }

    //Método del botón para mostrar la tirilla de pago
    public void addTirilloDePagoFragment(View view) {
        TirillaDePagoFragment tirillaDePago = new TirillaDePagoFragment();
        ((SellPanel) getActivity()).replaceFragments(tirillaDePago, R.id.panel, "TIRILLA_DE_PAGO");
        //Toast.makeText(this, "Prueba", Toast.LENGTH_SHORT).show();
    }

    public void updateText(String newText){
        String oldText = articleCode.getText().toString();
        int articleCodeLength = (oldText+newText).length();
        if(articleCodeLength<20)
            articleCode.setText(oldText+newText);
        else
            Toast.makeText(getContext(), "No se pueden colocar más números", Toast.LENGTH_SHORT).show();
    }

    public void deleteCharacter() {
        int articleCodeLength = articleCode.getText().length();
        if(articleCodeLength>0){
            String newText = articleCode.getText().toString().substring(0, articleCodeLength - 1);
            articleCode.setText(newText);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
