package com.jiyoung.andstudy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    private static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbSQL = new StringBuilder(128);
        sbSQL.append("CREATE TABLE ").append(TABLE_PRODUCTS).append("( ")
                .append(COLUMN_ID).append(" INTEGER PRIMARY KEY,")
                .append(COLUMN_PRODUCTNAME).append(" TEXT,")
                .append(COLUMN_QUANTITY).append(" INTEGER) ");
        sqLiteDatabase.execSQL(sbSQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductname());
        values.put(COLUMN_QUANTITY, product.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public  Product findProduct(String productname) {
        StringBuilder sbSQL = new StringBuilder(128);
        sbSQL.append("SELECT * FROM ").append(TABLE_PRODUCTS)
                .append(" WHERE ").append(COLUMN_PRODUCTNAME).append("=\"").append(productname).append("\"");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sbSQL.toString(), null);

        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product();
            product.setID(cursor.getInt(0));
            product.setProductName(cursor.getString(1));
            product.setQuantity(cursor.getInt(2));
            cursor.close();
        }
        db.close();
        return product;
    }

    public boolean deleteProduct(String productname) {

        boolean result = false;
        StringBuilder sbSQL = new StringBuilder(128);
        sbSQL.append("SELECT * FROM ").append(TABLE_PRODUCTS)
                .append(" WHERE ").append(COLUMN_PRODUCTNAME).append(" = \"").append(productname).append("\"");
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sbSQL.toString(), null);
        if (cursor.moveToFirst()) {
            Product product = new Product();
            product.setID(cursor.getInt(0));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
