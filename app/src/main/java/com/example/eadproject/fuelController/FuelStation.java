package com.example.eadproject.fuelController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.eadproject.QueueController.ViewLengthQueue;
import com.example.eadproject.R;
import com.example.eadproject.UserDashboard.Dashboard;

public class FuelStation extends AppCompatActivity {

    private Spinner spinnerFuelStation,spinnerFuelType,spinnerFuelCity;
    private String fuelType,city,station;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station);

        spinnerFuelStation = findViewById(R.id.ddFuelStationCity);
        spinnerFuelType = findViewById(R.id.ddUserFuelTypeStation);
        spinnerFuelCity = findViewById(R.id.ddFuelStationCity);
        nextButton= findViewById(R.id.btnFuelStationNext);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.fuelType, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapter2);

        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fuelType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewLengthQueue.class);
                startActivity(intent);
            }
        });

    }
}