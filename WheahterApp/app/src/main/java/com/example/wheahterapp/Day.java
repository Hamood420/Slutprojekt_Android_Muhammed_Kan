package com.example.wheahterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Day extends AppCompatActivity {

    Button get7DayForecastBtn;
    EditText ed7DayCityInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        get7DayForecastBtn = findViewById(R.id.get7DayForecastBtn);
        ed7DayCityInput = findViewById(R.id.et7DayCityInput);

        get7DayForecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Day2.class);
                intent.putExtra("7DayCityInput", ed7DayCityInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}