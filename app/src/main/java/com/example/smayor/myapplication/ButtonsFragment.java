package com.example.smayor.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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

    LinearLayout cancelButton;
    Button noSell;
    ArrayList<Classes.Button> buttons;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.try_layout_fragment, container, false);
        try {
            XmlPullParser parser = getResources().getXml(R.xml.teclado);
            XMLButtonParser buttonParser = new XMLButtonParser();
            buttons = buttonParser.getXMLfromResource(parser,"principal");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        Button mButton = new Button(getContext());
        mButton.setText("Prueba");
        LinearLayout lrl = new LinearLayout(getContext());
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lrl.setLayoutParams(rlp);
        lrl.setOrientation(LinearLayout.HORIZONTAL);
        lrl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        lrl.addView(mButton);
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
        rootView = lrl;
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
