package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Input.OnFragmentInteractionListener {

    private SQLiteDatabase db;
    private Button dbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbButton = findViewById(R.id.dbButton);

        dbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DBoutput.class);
                startActivity(intent);
            }
        });

        Button playerButton = findViewById(R.id.playerButton);

        playerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Player.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onFragmentInteraction(String link) {
        String info = dbUpdate(link);

        Output fragment = (Output) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_output);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(info);
        }
    }

    public String dbUpdate(String link) {
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
        }

        query.close();
        db.close();

        return info;
    }
}
