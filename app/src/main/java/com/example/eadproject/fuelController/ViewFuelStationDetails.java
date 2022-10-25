package com.example.eadproject.fuelController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.eadproject.R;
import com.example.eadproject.UserDashboard.Dashboard;

public class ViewFuelStationDetails extends AppCompatActivity {

    private Spinner spinnerFuelStation,spinnerFuelType;
    private String fuelType;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fuel_station_details);

        spinnerFuelStation = findViewById(R.id.ddViewUserFuelStation);
        spinnerFuelType = findViewById(R.id.ddViewUserFuelType);
        backButton= findViewById(R.id.btnbackViewUserFuelDetails);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

    }
}