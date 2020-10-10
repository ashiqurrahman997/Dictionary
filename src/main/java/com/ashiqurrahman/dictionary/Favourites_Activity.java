package com.ashiqurrahman.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Favourites_Activity extends AppCompatActivity {
    private static final String FAVOURITE_TABLE_NAME = "favourites";
    private Button button;
    private ArrayAdapter<String> listAdapter;
    private ListView listView;
    DatabaseRetrieve myDb;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_layout);
        this.listView = (ListView) findViewById(R.id.Bookmark_lst);
        this.button = (Button) findViewById(R.id.Clr_FavrtBtn);
        this.myDb = new DatabaseRetrieve(this);
        getWords();
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Favourites_Activity.this.myDb.deleteAllData(Favourites_Activity.FAVOURITE_TABLE_NAME).intValue() > 0) {
                    Toast.makeText(Favourites_Activity.this, "Data Deleted",Toast.LENGTH_SHORT).show();
                    Favourites_Activity.this.getWords();
                    return;
                }
                Toast.makeText(Favourites_Activity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void getWords() {
        this.listAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,new DatabaseRetrieve(this).dictionaryWords(FAVOURITE_TABLE_NAME));

        this.listView.setAdapter(this.listAdapter);
    }

}
