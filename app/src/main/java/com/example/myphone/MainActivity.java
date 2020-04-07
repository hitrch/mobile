package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Input.OnFragmentInteractionListener {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onFragmentInteraction(String link) {

        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS info (id INTEGER PRIMARY KEY AUTOINCREMENT, info TEXT)");

        ContentValues initialValues = new ContentValues();

        initialValues.put("info", link);

        db.insert("info", null, initialValues);

        Toast toast = Toast.makeText(getApplicationContext(), "Saved", 300);
        toast.show();


        Cursor query = db.rawQuery("SELECT * FROM info;", null);
        String info = "";

        if(query.moveToLast()){
            info = query.getString(1);
            info += "\nNumber of saves: " + query.getCount();
        }
        query.close();
        db.close();

        Output fragment = (Output) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_output);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(info);
        }
    }




}
