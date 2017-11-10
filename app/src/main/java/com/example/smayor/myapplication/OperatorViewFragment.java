package com.example.smayor.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OperatorViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OperatorViewFragment extends Fragment {
    TextView articleCode;

    private OnFragmentInteractionListener mListener;

    public OperatorViewFragment() {
        // Required empty public constructor
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_operator_view, container, false);

        articleCode = (TextView) rootView.findViewById(R.id.articleCode);
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
