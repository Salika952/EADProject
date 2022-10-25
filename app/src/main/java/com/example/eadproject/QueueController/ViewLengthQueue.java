package com.example.eadproject.QueueController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eadproject.R;
import com.example.eadproject.fuelController.ViewFuelDetails;

public class ViewLengthQueue extends AppCompatActivity {

    private Button enterButton,viewDetailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_length_queue);

        enterButton = findViewById(R.id.btnViewQueueDataEnter);
        viewDetailButton = findViewById(R.id.btnViewQueueDataViewFuelDetails);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddUserQueueDetails.class);
                startActivity(intent);
            }
        });
        viewDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewFuelDetails.class);
                startActivity(intent);
            }
        });

    }
}