package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DBoutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dboutput);

        showDB();
    }



    public void showDB(){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        Cursor query = db.rawQuery("SELECT * FROM info;", null);
        String info = "";

        if(query.moveToFirst()){
            do{
                info += "\nResult №" + query.getString(0) + "\n";
                info += query.getString(1) + "\n";
            }
            while(query.moveToNext());
        }

        TextView textView = findViewById(R.id.textView2);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(info);

        query.close();
        db.close();
    }
}
