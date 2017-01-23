package com.jiyoung.andstudy.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.jiyoung.andstudy.database.MyDBHandler;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.jiyoung.andstudy.provider.MyContentProvider";
    private static final String PRODUCT_TABLE = "products";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCT_TABLE);

    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCT_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCT_TABLE + "/#", PRODUCTS_ID);
    }

    private MyDBHandlerForProvider myDB;

    @Override
    public boolean onCreate() {
        //ContentProvider 인스턴스가 생성되어 초기화될때 호출됨 : 초기화에 필요한 작업을 수행
        myDB = new MyDBHandlerForProvider(getContext());
        return false;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //클라이언트에서 데이터 추가를 요청할때 호출됨

        //uri 타입을 체크하여 PRODUCT URI인 경우, contentValues를 새 데이터로 추가함
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case PRODUCTS :
                id = sqlDB.insert(MyDBHandler.TABLE_PRODUCTS, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //데이터가 조회 요청된 경우 호츨됨

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDBHandler.TABLE_PRODUCTS);

        int uriType = sURIMatcher.match(uri);
        switch(uriType) {
            case PRODUCTS_ID :
                queryBuilder.appendWhere(MyDBHandler.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case PRODUCTS :
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        Cursor cursor = queryBuilder.query(myDB.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri); //컨텐트 리졸버에게 통지

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        //기존 데이터의 변경 요청이 있을 경우 호출

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case PRODUCTS :
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, contentValues, selection, selectionArgs);
                break;
            case PRODUCTS_ID :
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, contentValues, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, contentValues, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case PRODUCTS :
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, selection, selectionArgs);
                break;
            case PRODUCTS_ID :
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(id)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }




}
