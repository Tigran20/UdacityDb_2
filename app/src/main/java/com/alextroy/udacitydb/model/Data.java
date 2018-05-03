package com.alextroy.udacitydb.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alextroy.udacitydb.adapter.InventoryAdapter;
import com.alextroy.udacitydb.db.InventoryContract;
import com.alextroy.udacitydb.db.InventoryDbHelper;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static InventoryDbHelper dbHelper;
    private static Data data;
    private static List<Product> products;

    public Data() {
    }

    public static List<Product> getProductsData(Context context) {
        if (data == null) {
            data = new Data();
        }
        if (products == null) {
            products = new ArrayList<>();
        } else {
            products.clear();
        }

        dbHelper = new InventoryDbHelper(context);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY
        };

        Cursor cursor = database.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try {
            int idColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                products.add(new Product(currentId, currentName, currentPrice, currentQuantity));
            }
        } finally {
            cursor.close();
        }
        return products;
    }

    public static void insertData(String productName, String productPrice, int quantity) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, productName);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE, productPrice);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        database.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, contentValues);
    }
}
