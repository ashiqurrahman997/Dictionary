package com.ashiqurrahman.dictionary;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.mainframe_layout);

      button=(Button)findViewById(R.id.bookmark);

        Typeface myface=Typeface.createFromAsset(getAssets(),"Algerian.ttf");
      //  button.setTypeface(myface);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b=new Intent(MainActivity.this,Favourites_Activity.class);
                b.putExtra("Username",button.getText().toString());
                startActivity(b);
            }
        });

      //  b.putExtra("Username",button.getText().toString());

    }

    public void meaning_btn_clk(View v)
    {

        Intent intent = new Intent(MainActivity.this, Word_Search_Activity.class);
        startActivity(intent);
    }
    public void quize_btn_clk(View v)
    {
        if (new DatabaseRetrieve(MainActivity.this).getAllID("history").size() < 5) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Learn atleast 5 words",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
    public void history_btn_clk(View v)
    {
       Intent intent = new Intent(MainActivity.this, History_Activity.class);
        startActivity(intent);
    }
    public void about_btn_clk(View v)
    {
        Intent intent = new Intent(MainActivity.this, About_activity.class);
        startActivity(intent);
    }
    public void bookmark_btn_clk(View v)
    {
      // Intent intent = new Intent(this, BookMark_Activity.class);
      //  startActivity(intent);
    }
}
