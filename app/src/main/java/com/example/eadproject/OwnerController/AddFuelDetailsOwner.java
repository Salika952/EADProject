package com.example.eadproject.OwnerController;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eadproject.DBHelper.DBHelper;
import com.example.eadproject.R;

public class AddFuelDetailsOwner extends AppCompatActivity {

    private Spinner spinnerFuelType,spinnerFinish;
    private String fuelType,finishStatus;
    private Button btn1,btn2,btn3;
    private Cursor cursor;
    private String email, id;
    DBHelper DB;
    private SQLiteDatabase sqLiteDatabaseObj;
    private EditText editTextStationName,getEditTextStationNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_queue_details_owner);

        spinnerFuelType = findViewById(R.id.ddFuelTypeOwner);
        spinnerFinish = findViewById(R.id.ddFuelFinishStatusOwner);
        btn1 = findViewById(R.id.btnAddFuelDetails);
        btn2 = findViewById(R.id.btnBackAddFuel);
        btn3 = findViewById(R.id.btnUpdateFuelOwner);
        editTextStationName = findViewById(R.id.txtAddFuelStationOwner);
        getEditTextStationNo = findViewById(R.id.txtAddFuelStationNoOwner);

        email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");

        DB = new DBHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fuelType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapter);

        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fuelType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.finishType, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFinish.setAdapter(adapter1);

        spinnerFinish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finishStatus = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddFuelDetailsOwner.this, "Adding", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OwnerDashboard.class);
                startActivity(intent);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateFuelDetails.class);
                startActivity(intent);
            }
        });

        editTextStationName.setEnabled(false);
        getEditTextStationNo.setEnabled(false);

        loadData();
    }

    @SuppressLint("Range")
    private void loadData() {
        sqLiteDatabaseObj = DB.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_2_Email + "=?", new String[]{email}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();

                String stName = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_8_StationName));
                String stNo = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_9_StationNo));

                editTextStationName.setText(stName);
                getEditTextStationNo.setText(stNo);

                // Closing cursor.
                cursor.close();
            }
        }
    }
}