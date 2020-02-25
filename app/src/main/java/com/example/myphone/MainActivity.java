package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HashMap<String, HashMap<String, String[]>> companyMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaring
        String[] phones = {"Кнопкодав", "Смартпхон"};
        String[] companies = {"Apple", "Android", "Xiaomi", "Samsung", "Nokia"};
        HashMap<String, String[]> applePhoneTypes = new HashMap<>();
        HashMap<String, String[]> androidPhoneTypes = new HashMap<>();
        HashMap<String, String[]> xiaomiPhoneTypes = new HashMap<>();
        HashMap<String, String[]> samsungPhoneTypes = new HashMap<>();
        HashMap<String, String[]> nokiaPhoneTypes = new HashMap<>();


        //filling company phone types
        String[] applePhones = new String[]{"IPhone1", "IPhone2", "IPhone3", "IPhone4"};

        String[] androidPhone = {"Android1", "Android2"};

        String[] xiaomiPhone = {"Xiaomi"};

        String[] samsungButtonPhone = {"Samsung1", "Samsung2"};
        String[] samsungSmartPhone = {"Samsung3", "Samsung4"};

        String[] nokiaPhone = {"Nokia1", "Nokia2", "Nokia3", "Nokia4"};

        applePhoneTypes.put("Смартпхон", applePhones);

        androidPhoneTypes.put("Смартпхон", androidPhone);

        xiaomiPhoneTypes.put("Смартпхон", xiaomiPhone);

        samsungPhoneTypes.put("Кнопкодав", samsungButtonPhone);
        samsungPhoneTypes.put("Смартпхон", samsungSmartPhone);

        nokiaPhoneTypes.put("Кнопкодав", nokiaPhone);


        //filling companies
        companyMap.put("Apple", applePhoneTypes);
        companyMap.put("Android", androidPhoneTypes);
        companyMap.put("Xiaomi", xiaomiPhoneTypes);
        companyMap.put("Samsung", samsungPhoneTypes);
        companyMap.put("Nokia", nokiaPhoneTypes);


        for (Map.Entry<String, String[]> entry : applePhoneTypes.entrySet()) {
            String key = entry.getKey();

            System.out.print(key);
        }


        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phones);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);


        //making TextView scrolable
        TextView textView = findViewById(R.id.textView2);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onMyButtonClick(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        RadioGroup rg = findViewById(R.id.rg);
        RadioButton selectedButton = findViewById(rg.getCheckedRadioButtonId());


        TextView textView = findViewById(R.id.textView2);

        //getting selected values
        String selectedPhoneType = spinner.getSelectedItem().toString();
        String selectedCompany = "";

        if(selectedButton != null){
            selectedCompany = selectedButton.getText().toString();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Select company", 300);
            toast.show();
        }


        String buffer = "";

        HashMap<String, String[]> companyCheck = companyMap.get(selectedCompany);

        //checking for nulls
        if (companyCheck != null) {
            String[] phoneArray = companyCheck.get(selectedPhoneType);

            if (phoneArray != null) {
                for (int i = 0; i < phoneArray.length; i++) {
                    buffer += phoneArray[i] + "\n";
                }
            } else {
                buffer = "No phones found";
            }
        } else {
            buffer = "No company found";
        }

        textView.setText(buffer);
    }
}
