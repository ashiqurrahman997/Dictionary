package com.ashiqurrahman.dictionary;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

public class Meaning_Show extends AppCompatActivity {
    private static final String FAVOURITE_TABLE_ID_NAME = "fav_id";
    private static final String FAVOURITE_TABLE_NAME = "favourites";
    private static final String TABLE_ID_NAME = "history_id";
    private static final String TABLE_NAME = "history";
    private TextView antonymShow;
    private Button favbutton;
    DataBaseHandler myDB;
    private TextView sentenceShow;
    private TextView synonymShow;
    private TextView word;
    private TextView wordMeaning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meaning_show_layout);
        this.favbutton = (Button) findViewById(R.id.fab_btn);
        this.word = (TextView) findViewById(R.id.wordbr);
        this.wordMeaning = (TextView) findViewById(R.id.meaning);
        this.synonymShow = (TextView) findViewById(R.id.synonym);
        this.antonymShow = (TextView) findViewById(R.id.antonym);
        this.sentenceShow = (TextView) findViewById(R.id.systence);

        this.myDB = new DataBaseHandler(this);

        final int id = getIntent().getExtras().getInt("DICTIONARY_ID") + 1;
        final DictionaryObject selectedWord = new DatabaseRetrieve(this).getWordById(id);
        this.word.setText(selectedWord.getWord());
        this.wordMeaning.setText(selectedWord.getMeaning());
        this.synonymShow.setText("Synonym:\n"+selectedWord.getSynonym());
        this.antonymShow.setText("Antonym:\n"+selectedWord.getAntonym());
        this.sentenceShow.setText(selectedWord.getSentence());
        Cursor cursor = new DataBaseHandler(this).getDbConnection_Read().rawQuery("select * from history where history_id= " + id, null);
        if (!cursor.moveToFirst()) {
            AddData(id, selectedWord.getWord(), selectedWord.getMeaning(), TABLE_NAME, TABLE_ID_NAME);
        }
        cursor.close();
     this.favbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Meaning_Show.this.addDataToFavourites(id, selectedWord.getWord(), selectedWord.getMeaning());
                Toast.makeText(Meaning_Show.this,"Data inseted to Favourite", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addDataToFavourites(int id, String selectedWord, String wordMeaning) {
        Cursor cursor = new DataBaseHandler(this).getDbConnection_Read().rawQuery("select * from favourites where fav_id= " + id, null);
        if (!cursor.moveToFirst()) {
            AddData(id, selectedWord, wordMeaning, FAVOURITE_TABLE_NAME, FAVOURITE_TABLE_ID_NAME);
        }
        cursor.close();
    }

    public void AddData(int id, String word, String meaning, String TABLE_NAME, String ID_NAME) {
        if (this.myDB.insertData(id, word, meaning, TABLE_NAME, ID_NAME)) {
          // Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        } else {
           Toast.makeText(this, "No data inserted to history", Toast.LENGTH_SHORT).show();
        }
    }
}
