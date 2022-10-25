package com.example.eadproject.UserDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eadproject.DBHelper.DBHelper;
import com.example.eadproject.QueueController.UserViewQueueAllDetails;
import com.example.eadproject.R;
import com.example.eadproject.fuelController.FuelStation;
import com.example.eadproject.fuelController.ViewFuelStationDetails;
import com.example.eadproject.userController.Login;
import com.example.eadproject.userController.UserProfile;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {

    private SwipeRefreshLayout layout;
    private CardView card1,card2,card3,card4;
    private SQLiteDatabase sqLiteDatabaseObj;
    private Cursor cursor;
    private String email,id;
    DBHelper DB;
    private TextView textViewMame;
    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        email = getIntent().getStringExtra("email");

        card1 = findViewById(R.id.card1);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card2 = findViewById(R.id.card2);
        textViewMame = findViewById(R.id.textViewDashboardUser);
        layout = findViewById(R.id.dashboardRefresh);
        button =  findViewById(R.id.userLogout);

        DB = new DBHelper(this);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, ViewFuelStationDetails.class);
                // Sending Email to Dashboard Activity using intent.
//                intent.putExtra("email", email);
//                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, UserViewQueueAllDetails.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, FuelStation.class);
                // Sending Email to Dashboard Activity using intent.
//                intent.putExtra("email", email);
//                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, UserProfile.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                layout.setRefreshing(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
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
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_1_Name));
                id = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_ID));

                textViewMame.setText("Hello!" +" " +name);
                // Closing cursor.
                cursor.close();
            }
        }
    }
}