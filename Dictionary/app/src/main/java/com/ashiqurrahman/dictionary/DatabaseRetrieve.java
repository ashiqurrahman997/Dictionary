package com.ashiqurrahman.dictionary;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

public class DatabaseRetrieve extends DataBaseHandler {
    public DatabaseRetrieve(Context context) {
        super(context);
    }

    public String[] dictionaryWords(String TABLE_NAME) {
        Cursor cursor = getDbConnection_Read().rawQuery("Select * from " + TABLE_NAME, null);
        ArrayList<String> wordTerms = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                wordTerms.add(cursor.getString(cursor.getColumnIndexOrThrow("word")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return (String[]) wordTerms.toArray(new String[wordTerms.size()]);
    }

    public DictionaryObject getWordById(int id) {
        DictionaryObject wordObject = null;
        Cursor cursor = getDbConnection_Read().rawQuery("select * from dictionary where id = " + id, null);
        if (cursor.moveToFirst()) {
            do {
                wordObject = new DictionaryObject(cursor.getString(cursor.getColumnIndexOrThrow("word")), cursor.getString(cursor.getColumnIndexOrThrow("meaning")), cursor.getString(cursor.getColumnIndexOrThrow("synonym")), cursor.getString(cursor.getColumnIndexOrThrow("antonym")), cursor.getString(cursor.getColumnIndexOrThrow("sentence")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return wordObject;
    }

    public ArrayList<Integer> getAllID(String TABLE_NAME) {
        Cursor cursor = getDbConnection_Read().rawQuery("Select * from " + TABLE_NAME, null);
        ArrayList<Integer> idTerms = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                idTerms.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("history_id"))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return idTerms;
    }
}
