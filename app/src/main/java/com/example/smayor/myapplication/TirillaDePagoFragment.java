package com.example.smayor.myapplication;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TirillaDePagoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class TirillaDePagoFragment extends Fragment {

    public TirillaDePagoFragment() {
        // Required empty public constructor
    }

    Button backToPanel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tirilla_de_pago, container, false);
        backToPanel = (Button) rootView.findViewById(R.id.backToPanel);
        final Fragment myFrag = this;
        backToPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonsFragment buttonsFragment = new ButtonsFragment();
                ((SellPanel) getActivity()).removeFragment(myFrag);
            }
        });
        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
