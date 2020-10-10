package com.ashiqurrahman.dictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class History_Activity extends AppCompatActivity {

    private static final String TABLE_NAME = "history";
    private Button button;
    private ArrayAdapter<String> listAdapter;
    private ListView listView;
    DatabaseRetrieve myDb;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        this.listView = (ListView) findViewById(R.id.histroy_lst);
        this.button = (Button) findViewById(R.id.Clr_History);
        this.myDb = new DatabaseRetrieve(this);
        getWords();
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (History_Activity.this.myDb.deleteAllData(History_Activity.TABLE_NAME).intValue() > 0) {
                    Toast.makeText(History_Activity.this, "Data Deleted",Toast.LENGTH_SHORT).show();
                    History_Activity.this.getWords();
                    return;
                }
                Toast.makeText(History_Activity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void getWords() {
       this.listAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,new DatabaseRetrieve(this).dictionaryWords(TABLE_NAME));

        this.listView.setAdapter(this.listAdapter);
    }

}
