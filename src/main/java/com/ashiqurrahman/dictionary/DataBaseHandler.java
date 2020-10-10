package com.ashiqurrahman.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHandler {
    private static DatabaseCreat dbHelper;
    private SQLiteDatabase db ;
    private SQLiteDatabase db1;

    public DataBaseHandler(Context context) {
        dbHelper = new DatabaseCreat(context);
        db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection_Read()
    {
        return this.db;
    }

    public SQLiteDatabase getDbConnection_Write() {
        this.db1 = dbHelper.getWritableDatabase();
        return this.db1;
    }

    public void closeDbconnection() {
        if (this.db != null) {
            this.db.close();
        }
    }

    public void closeDb1connection() {
        if (this.db1 != null) {
            this.db1.close();
        }
    }

    public boolean insertData(int id, String word, String meaning, String TABLE_NAME, String id_name) {
        SQLiteDatabase setData = getDbConnection_Write();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_name, Integer.valueOf(id));
        contentValues.put("word", word);
        contentValues.put("meaning", meaning);
        if (setData.insert(TABLE_NAME, null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public Integer deleteAllData(String TABLE_NAME) {
        return Integer.valueOf(getDbConnection_Write().delete(TABLE_NAME, null, null));
    }
}
