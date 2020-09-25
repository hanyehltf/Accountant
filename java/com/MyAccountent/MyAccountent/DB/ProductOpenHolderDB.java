package com.MyAccountent.MyAccountent.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.MyAccountent.MyAccountent.Adaptors.ProductTableAdaptor;
import com.MyAccountent.MyAccountent.Data.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductOpenHolderDB extends SQLiteOpenHelper {
    private ProductTableAdaptor productTableAdaptor;
    private static final String TAG = "DatabaseOpenHelper";
    public final static String DATABASE_NAME = "Accountent";
    public final static String COL_NAME = "col_name";
    public final static String COL_PRICE = "col_price";
    public final static String COL_COUNT = "col_count";
    public final static String TABLE_NAME = "col_name";
    public final static String SQL_COMMAND_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS  "+ TABLE_NAME
           + " ( " + COL_NAME + " TEXT ," +
            COL_PRICE + " TEXT," +
            COL_COUNT + " TEXT );";

    private Context context;

    public ProductOpenHolderDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND_CREATE_TABLE);
        }
catch (SQLException e){
    Log.i("DatabaseError","on create "+e.toString());
}

    }





    public void  AddProduct(Product product){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME,product.getName());
        contentValues.put(COL_PRICE,product.getPrice());
        contentValues.put(COL_COUNT,product.getCount());
long i=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
sqLiteDatabase.close();
Log.i(TAG,"add product to database : "+i);



    }
    public List<Product>getProduct(){
        List products=new ArrayList();
SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            while (!cursor.isAfterLast()){
                Product product=new Product();
                product.setName(cursor.getString(0));
                product.setPrice(cursor.getString(1));
                product.setCount(cursor.getInt(2));
products.add(product);
cursor.moveToNext();

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return products;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
