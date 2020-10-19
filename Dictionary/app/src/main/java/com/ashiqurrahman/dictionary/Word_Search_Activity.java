package com.ashiqurrahman.dictionary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Word_Search_Activity extends AppCompatActivity {

    private static final String TABLE_NAME = "dictionary";

    private   Button search_btn;
    private   EditText searchbar;
    private    ListView listView;
    private   ArrayList<String> arrayList;
    private int count=1;
    private  String[] string;
    ArrayAdapter<String>arrayAdapter;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_search_layout);
        search_btn=(Button)findViewById(R.id.search_btn);
        searchbar=(EditText)findViewById(R.id.search_bar);
        listView=(ListView)findViewById(R.id.listbar);
        arrayList=new ArrayList<>();

        arrayList.add("knowledge");
        arrayList.add("important");
        arrayList.add("Good");

    DatabaseRetrieve dr=new DatabaseRetrieve(this);
        string=dr.dictionaryWords(TABLE_NAME);
          for( String v:string)
          {
             Log.d("Word",v);
          }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,string);

        listView.setAdapter(arrayAdapter);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          String word=  Word_Search_Activity.this.arrayAdapter.getItem(position);
            Toast.makeText(getApplicationContext(),word,Toast.LENGTH_SHORT).show();

            int index = 0;
            for (int i = 0; i <string.length; i++) {
                if (string[i].equals(word)) {
                    index = i;
                    break;
                }
            }
            Intent intent =new Intent(Word_Search_Activity.this,Meaning_Show.class);
            intent.putExtra("DICTIONARY_ID", index);
            startActivity(intent);
        }
    });

    this.searchbar.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Word_Search_Activity.this.arrayAdapter.getFilter().filter(s);

        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    });



    }

}
