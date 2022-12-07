package com.example.wheahterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class City extends AppCompatActivity {

    TextView tvCityAndCountry, tvTemp, tvFeelsLike, tvDescription;
    Button search7DayForecastBtn;

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e66541e907b54872420d0bb1e45ea93c";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        tvCityAndCountry = findViewById(R.id.countryAndCity);
        tvTemp = findViewById(R.id.temp);
        tvFeelsLike = findViewById(R.id.feelsLike);
        tvDescription = findViewById(R.id.description);
        search7DayForecastBtn = findViewById(R.id.search7DayForecastBtn);
        String cityInput = getIntent().getStringExtra("TodayCityInput");

        String city = cityInput;
        if (city.equals("")) {
            Toast.makeText(City.this, "Enter a city", Toast.LENGTH_SHORT).show();
        } else {

            String tempUrl = url + "?q=" + city + "&appid=" + appid;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);

                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");

                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;

                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");

                        String cityName = jsonResponse.getString("name");


                        tvCityAndCountry.setText(cityName + "(" + countryName + ")");
                        tvTemp.setText(df.format(temp) + "C");
                        tvFeelsLike.setText(df.format(feelsLike) + "C");
                        tvDescription.setText(description);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }


        search7DayForecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Day.class);
                startActivity(intent);
            }
        });


    }
}
