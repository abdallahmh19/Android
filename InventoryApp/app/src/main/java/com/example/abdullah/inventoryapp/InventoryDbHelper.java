package com.example.abdullah.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Abdullah on 5/11/2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "store.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_PRODUCT_TABLE =
                " CREATE TABLE " + InventoryContract.ProductEntry.TABLE_NAME +"("
                        +InventoryContract.ProductEntry.COLUMN_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +InventoryContract.ProductEntry.COLUMN_NAME +"  TEXT UNIQUE NOT NULL, "
                        +InventoryContract.ProductEntry.COLUMN_PRICE+" INTEGER NOT NULL,"
                        +InventoryContract.ProductEntry.COLUMN_QUANTITY +" INTEGER NOT NULL,"
                        +InventoryContract.ProductEntry.COLUMN_IMAGE +" BLOB NOT NULL"+
                        ");";
        Log.d(LOG_TAG, "onCreate: " + SQL_CREATE_PRODUCT_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + InventoryContract.ProductEntry.TABLE_NAME);
        onCreate(db);
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    public boolean insertData (String name , int quantity  ,int price , byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.ProductEntry.COLUMN_NAME,name);
        values.put(InventoryContract.ProductEntry.COLUMN_PRICE,price);
        values.put(InventoryContract.ProductEntry.COLUMN_QUANTITY,quantity);
        values.put(InventoryContract.ProductEntry.COLUMN_IMAGE,image);
        db.insert(InventoryContract.ProductEntry.TABLE_NAME,null,values);
        return true;
    }
    public int updateData(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(InventoryContract.ProductEntry.COLUMN_NAME,product.getName());
        values.put(InventoryContract.ProductEntry.COLUMN_PRICE,product.getPrice());
        values.put(InventoryContract.ProductEntry.COLUMN_QUANTITY,product.getQuantity());
        values.put(InventoryContract.ProductEntry.COLUMN_IMAGE,product.getImage());

        return db.update(InventoryContract.ProductEntry.TABLE_NAME,values,"_id= ?",new String[]{""+ product.getId()});

    }
    public ArrayList<Product> getAllData() {
        ArrayList<Product> productList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor getList = db.rawQuery("SELECT * FROM " + InventoryContract.ProductEntry.TABLE_NAME, null);
        getList.moveToFirst();
        while (getList.isAfterLast() == false) {
            Product pr = new Product();

            pr.setId(getList.getInt(getList.getColumnIndex(InventoryContract.ProductEntry._ID)));
            pr.setName(getList.getString(getList.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME)));
            pr.setPrice(getList.getInt(getList.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRICE))) ;
            pr.setQuantity(getList.getInt(getList.getColumnIndex(InventoryContract.ProductEntry.COLUMN_QUANTITY)));
            pr.setImage(getList.getBlob(getList.getColumnIndex(InventoryContract.ProductEntry.COLUMN_IMAGE)));
            productList.add(pr);
            getList.moveToNext();
        }
        return productList;
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(InventoryContract.ProductEntry.TABLE_NAME, "_id=?", new String[]{""+id}) > 0;
    }
}
