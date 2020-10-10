package com.ashiqurrahman.dictionary;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseCreat extends SQLiteAssetHelper {

    private static final String DATABASE_NAMES = "dictionaryDatabase.db";
    private static final int DATABASE_VERSION = 5;

    public DatabaseCreat(Context context) {
        super(context, DATABASE_NAMES, null, 5);
    }
}
