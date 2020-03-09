package com.example.myphone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class Output extends Fragment {

    public Output() {
        // Required empty public constructor
    }

    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.textView);
        view.setMovementMethod(new ScrollingMovementMethod());
        view.setText(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_output, container, false);
    }






}
