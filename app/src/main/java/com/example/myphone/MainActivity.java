package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Input.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onFragmentInteraction(String link) {
        Output fragment = (Output) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_output);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }


}
