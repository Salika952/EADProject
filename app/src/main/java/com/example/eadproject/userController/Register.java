package com.example.eadproject.userController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eadproject.DBHelper.DBHelper;
import com.example.eadproject.R;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private TextView textView,sigin;
    private EditText editTextName,editTextEmail,editTextMobile,editTextVehicleNo1,editTextVehicleNo2,
            editTextAddress,editTextStationName,editTextStationNo,editTextPassword,editTextRetypepassowrd;
    private Spinner spinnerRole,spinnerVehicelType,spinnerFuelType,editTextCity;
    private Button button;
    private String roleType,fuelType,vehicleType,cityType;
    private String name,email,mobile,vehicleNo1,vehicleNo2,city,address,stationName,stationNo,vehicleTypeAdd,fuelTypeAdd,password,rePassword,role;
    private LinearLayout layout1, layout2;
    SQLiteDatabase sqLiteDatabaseObj;
    DBHelper DB;
    String SQLiteDataBaseQueryHolder ;
    Boolean EditTextEmptyHolder;
    String F_Result = "Not_Found";
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.txtRegisterFullName);
        editTextEmail = findViewById(R.id.txtRegisterEmail);
        editTextMobile = findViewById(R.id.txtRegisterMobile);
        editTextVehicleNo1 = findViewById(R.id.txtRegisterVehicleNo1);
        editTextVehicleNo2 = findViewById(R.id.txtRegisterVehicleNo2);
        editTextCity = findViewById(R.id.ddRegisterCity);
        editTextAddress = findViewById(R.id.txtRegisterAddress);
        editTextStationName = findViewById(R.id.txtRegisterStationName);
        editTextStationNo = findViewById(R.id.txtRegisterStationNo);
        editTextPassword = findViewById(R.id.txtRegisterPassword);
        editTextRetypepassowrd = findViewById(R.id.txtRegisterRetypePassword);
        spinnerRole = findViewById(R.id.ddRegisterRole);
        spinnerVehicelType = findViewById(R.id.ddRegisterVehicleType);
        spinnerFuelType = findViewById(R.id.ddRegisterFuelType);
        layout1 = findViewById(R.id.customerLayer);
        layout2 = findViewById(R.id.ownerLayer);
        button = findViewById(R.id.btnRegisterCreateAccount);
        sigin = findViewById(R.id.txtRegisterSingIn);

        DB = new DBHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                roleType = adapterView.getItemAtPosition(i).toString();

                if( roleType.equals("User")){
                    layout2.setVisibility(View.GONE);
                    layout1.setVisibility(View.VISIBLE);
                    editTextVehicleNo1.setText(null);
                    editTextVehicleNo2.setText(null);
                    editTextAddress.setText(null);
                    editTextStationName.setText(null);
                    editTextStationNo.setText(null);
                }else if(roleType.equals("Owner")){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    editTextVehicleNo1.setText(null);
                    editTextVehicleNo2.setText(null);
                    editTextAddress.setText(null);
                    editTextStationName.setText(null);
                    editTextStationNo.setText(null);
                }else if(roleType.equals("Choose")){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    editTextVehicleNo1.setText(null);
                    editTextVehicleNo2.setText(null);
                    editTextAddress.setText(null);
                    editTextStationName.setText(null);
                    editTextPassword.setText(null);
                    editTextStationNo.setText(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.vehicleType, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicelType.setAdapter(adapter1);

        spinnerVehicelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vehicleType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

        editTextCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.cityType, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextCity.setAdapter(adapter4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDataBaseBuild();
                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();
                // Checking EditText is empty or Not.
                CheckEditTextStatus();
                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();
                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();
            }
        });
    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "
                + DBHelper.TABLE_NAME
                + "(" + DBHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBHelper.Table_Column_1_Name + " VARCHAR, "
                + DBHelper.Table_Column_2_Email + " VARCHAR, "
                + DBHelper.Table_Column_3_Mobile + " VARCHAR, "
                + DBHelper.Table_Column_4_VehicleNo1 + " VARCHAR, "
                + DBHelper.Table_Column_5_VehicleNo2 + " VARCHAR, "
                + DBHelper.Table_Column_6_CIty + " VARCHAR, "
                + DBHelper.Table_Column_7_Address + " VARCHAR, "
                + DBHelper.Table_Column_8_StationName + " VARCHAR, "
                + DBHelper.Table_Column_9_StationNo + " VARCHAR, "
                + DBHelper.Table_Column_10_VehicleType + " VARCHAR, "
                + DBHelper.Table_Column_11_FuelType + " VARCHAR, "
                + DBHelper.Table_Column_12_RoleType + " VARCHAR, "
                + DBHelper.Table_Column_13_Password + " VARCHAR);");
    }

    public void InsertDataIntoSQLiteDatabase(){
        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true)
        {
            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO " +DBHelper.TABLE_NAME +" (name,email,mobileNo,vehicleNo1,vehicleNo2,city,address,stationName,stationNo,vehicleType,fuelType,role,password) VALUES('" +name +"', '" +email +"', '" +mobile +"', '" +vehicleNo1 +"', '" +vehicleNo2 +"', '" +city +"', '" +address +"', '" +stationName +"', '" +stationNo +"', '" +vehicleType +"', '" +fuelType +"', '" +role +"', '" +password +"');";
            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            // Closing SQLite database object.
            sqLiteDatabaseObj.close();
            // Printing toast message after done inserting.
            Toast.makeText(Register.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            Toast.makeText(Register.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }
    public void EmptyEditTextAfterDataInsert(){
        editTextName.getText().clear();
        editTextEmail.getText().clear();
        editTextMobile.getText().clear();
        editTextVehicleNo1.getText().clear();
        editTextVehicleNo2.getText().clear();
        editTextAddress.getText().clear();
        editTextStationName.getText().clear();
        editTextStationNo.getText().clear();
        editTextPassword.getText().clear();
    }
    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
         name = editTextName.getText().toString();
         email = editTextEmail.getText().toString();
         mobile = editTextMobile.getText().toString();
         vehicleNo1 = editTextVehicleNo1.getText().toString();
         vehicleNo2 = editTextVehicleNo2.getText().toString();
         city = cityType;
         address = editTextAddress.getText().toString();
         stationName = editTextStationName.getText().toString();
         stationNo = editTextStationNo.getText().toString();
         vehicleTypeAdd = vehicleType;
         fuelTypeAdd = fuelType;
         password = editTextPassword.getText().toString();
         rePassword = editTextRetypepassowrd.getText().toString();
         role = roleType;

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }
    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = DB.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_2_Email + "=?", new String[]{email}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";
                // Closing cursor.
                cursor.close();
            }
        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();
    }
    // Checking result
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {
            // If email is exists then toast msg will display.
            Toast.makeText(Register.this,"Email Already Exists",Toast.LENGTH_LONG).show();
        }
        else {
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found" ;
    }
}