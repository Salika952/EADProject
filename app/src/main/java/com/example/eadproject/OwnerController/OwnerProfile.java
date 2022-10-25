package com.example.eadproject.OwnerController;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eadproject.DBHelper.DBHelper;
import com.example.eadproject.R;
import com.example.eadproject.userController.Login;
import com.example.eadproject.userController.UserProfile;

public class OwnerProfile extends AppCompatActivity {

    private EditText fullnameEditText, emailEditText, mobileEditText,
            roleEditText, StationNameEditText, StationNoEditText, cityEditText, addressEditText;
    ;
    private SQLiteDatabase sqLiteDatabaseObj;
    private Cursor cursor;
    private String email, id;
    DBHelper DB;
    private Button update, delete;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");

        fullnameEditText = findViewById(R.id.txtOwnerProfilesName);
        emailEditText = findViewById(R.id.txtOwnerProfilesEmail);
        mobileEditText = findViewById(R.id.txtOwnerProfilesMobile);
        roleEditText = findViewById(R.id.txtOwnerProfilesRole);
        StationNameEditText = findViewById(R.id.txtOwnerProfilesStationName);
        StationNoEditText = findViewById(R.id.txtOwnerProfilesStationNo);
        cityEditText = findViewById(R.id.txtOwnerProfilesCity);
        addressEditText = findViewById(R.id.txtOwnerProfilesAddress);
        update = findViewById(R.id.btnOwnerProfileUpdate);
        delete = findViewById(R.id.btnOwnerProfileBack);

        emailEditText.setEnabled(false);
        roleEditText.setEnabled(false);

        builder = new AlertDialog.Builder(this);
        DB = new DBHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Delete...!")
                        .setMessage("Do you want to delete the user")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean status = deleteData(id);
                                if (status == true) {
                                    Toast.makeText(OwnerProfile.this, "Delete SuccessFully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(OwnerProfile.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullnameEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String stationName = StationNameEditText.getText().toString();
                String stationNo = StationNoEditText.getText().toString();


                boolean status = updateData(name, mobile, city, address, stationName, stationNo, id);
                if (status == true) {
                    Toast.makeText(OwnerProfile.this, "Update SuccessFully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), OwnerDashboard.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(OwnerProfile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadData();
    }

    private boolean updateData(String name, String mobile, String city, String address, String stationName, String stationNo, String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.Table_Column_1_Name, name);
        contentValues.put(DBHelper.Table_Column_3_Mobile, mobile);
        contentValues.put(DBHelper.Table_Column_6_CIty, city);
        contentValues.put(DBHelper.Table_Column_7_Address, address);
        contentValues.put(DBHelper.Table_Column_8_StationName, stationName);
        contentValues.put(DBHelper.Table_Column_9_StationNo, stationNo);

        return sqLiteDatabaseObj.update(DBHelper.TABLE_NAME, contentValues, DBHelper.Table_Column_ID + "=?", new String[]{id}) > 0;
    }

    private boolean deleteData(String id) {
        return sqLiteDatabaseObj.delete(DBHelper.TABLE_NAME, DBHelper.Table_Column_ID + "=?", new String[]{id}) > 0;
    }

    @SuppressLint("Range")
    private void loadData() {
        sqLiteDatabaseObj = DB.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_2_Email + "=?", new String[]{email}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                // Storing Password associated with entered email.
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_1_Name));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_2_Email));
                String mobile = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_3_Mobile));
                String city = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_6_CIty));
                String address = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_7_Address));
                String stName = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_8_StationName));
                String stNo = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_9_StationNo));
                String role = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_12_RoleType));

                fullnameEditText.setText(name);
                emailEditText.setText(email);
                mobileEditText.setText(mobile);
                roleEditText.setText(role);
                cityEditText.setText(city);
                addressEditText.setText(address);
                StationNameEditText.setText(stName);
                StationNoEditText.setText(stNo);

                // Closing cursor.
                cursor.close();
            }
        }
    }
}