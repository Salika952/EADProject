package com.example.eadproject.QueueController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eadproject.DBHelper.DBHelper;
import com.example.eadproject.R;

public class UserViewQueueAllDetails extends AppCompatActivity {

    private TextView name,vehicleType, vehicleNo, fuelType;
    private SQLiteDatabase sqLiteDatabaseObj;
    private Cursor cursor;
    private String email, id;
    DBHelper DB;
    private SwipeRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_queue_all_details);
        email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");

        name = findViewById(R.id.textViewQueueUserName);
        vehicleType = findViewById(R.id.textViewQueueVehicleType);
        vehicleNo = findViewById(R.id.textViewQueueVehicleNo);
        fuelType = findViewById(R.id.textViewQueueFuelType);
        layout = findViewById(R.id.viewQueueRefresh);

        DB = new DBHelper(this);

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                layout.setRefreshing(false);
            }
        });
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
                // Storing Password associated with entered email.
                String name1 = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_1_Name));
                String vehicleType1 = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_10_VehicleType));
                String vehicleNo11 = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_4_VehicleNo1));
                String vehicleNo21 = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_5_VehicleNo2));
                String fuelType1 = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_11_FuelType));

                name.setText(name1);
                vehicleType.setText(vehicleType1);
                vehicleNo.setText(vehicleNo11 + "-" + vehicleNo21);
                fuelType.setText(fuelType1);
                // Closing cursor.
                cursor.close();
            }
        }
    }
}