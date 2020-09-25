package com.MyAccountent.MyAccountent.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.MyAccountent.MyAccountent.Adaptors.ServiceTableAdaptor;
import com.MyAccountent.MyAccountent.Data.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceOpenHolderDB extends SQLiteOpenHelper {
    private ServiceTableAdaptor serviceTableAdaptor;
    private static final String TAG = "DatabaseOpenHelper";
    public final static String DATABASE_NAME = "Service";
    public final static String COL_NAME = "col_name";
    public final static String COL_PRICE = "col_price";
    public final static String COL_Time = "col_count";
    public final static String TABLENAME = "services";
    public final static String SQLCOMMANDCREATETABLE = " CREATE TABLE " + TABLENAME
            + " ( " + COL_NAME + " TEXT ," +
            COL_PRICE + " TEXT," +
            COL_Time + " TEXT ) ;";

    public ServiceOpenHolderDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d("tableCreate", "onCreate: created");
            db.execSQL(SQLCOMMANDCREATETABLE);
        } catch (SQLException e) {
            Log.i("DatabaseError", "on create " + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddService(Service service) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, service.getName());
        contentValues.put(COL_PRICE, service.getPrice());
        contentValues.put(COL_Time, service.getTime());
        long i = sqLiteDatabase.insert(TABLENAME, null, contentValues);
        sqLiteDatabase.close();
        Log.i(TAG, "add service to database : " + i);

    }


    public List getService() {
        List<Service> services = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLENAME, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Service service = new Service();
                service.setName(cursor.getString(0));
                service.setPrice(cursor.getString(1));
                service.setTime(cursor.getString(2));
                services.add(service);
                cursor.moveToNext();

            }
        }
        cursor.close();
        sqLiteDatabase.close();


        return services;

    }

}
