package com.example.eadproject.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.UUID;

public class DBHelper extends SQLiteOpenHelper {

    UUID uuid = UUID.randomUUID();
    String uuidAsString = uuid.toString();

    public static final String DATABASE_NAME = "UserDataBase.db";
    public static final String TABLE_NAME="UserTable";
    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_Email="email";
    public static final String Table_Column_3_Mobile="mobileNo";
    public static final String Table_Column_4_VehicleNo1="vehicleNo1";
    public static final String Table_Column_5_VehicleNo2="vehicleNo2";
    public static final String Table_Column_6_CIty="city";
    public static final String Table_Column_7_Address="address";
    public static final String Table_Column_8_StationName="stationName";
    public static final String Table_Column_9_StationNo="stationNo";
    public static final String Table_Column_10_VehicleType="vehicleType";
    public static final String Table_Column_11_FuelType="fuelType";
    public static final String Table_Column_12_RoleType="role";
    public static final String Table_Column_13_Password="password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ("+Table_Column_ID+" INTEGER PRIMARY KEY, " +Table_Column_1_Name+" VARCHAR, " +Table_Column_2_Email+" VARCHAR, " +Table_Column_3_Mobile+" VARCHAR, " +Table_Column_4_VehicleNo1+" VARCHAR, " +Table_Column_5_VehicleNo2+" VARCHAR, " +Table_Column_6_CIty+" VARCHAR, " +Table_Column_7_Address+" VARCHAR, " +Table_Column_8_StationName+" VARCHAR, " +Table_Column_9_StationNo+" VARCHAR, " +Table_Column_10_VehicleType+" VARCHAR, " +Table_Column_11_FuelType+" VARCHAR, " +Table_Column_12_RoleType+" VARCHAR, " +Table_Column_13_Password+" VARCHAR)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
