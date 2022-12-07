package com.example.wheahterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button getTodaysWheaterBtn;
    EditText etTodayCityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTodaysWheaterBtn = findViewById(R.id.getTodaysWeatherBtn);
        etTodayCityInput = findViewById(R.id.etTodayCityInput);

        getTodaysWheaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),City.class );
                intent.putExtra("TodayCityInput", etTodayCityInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}