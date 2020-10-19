package com.ashiqurrahman.dictionary;

import android.content.Intent;
import android.net.LinkAddress;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;
public class Animation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        LinearLayout l=(LinearLayout)findViewById(R.id.lin_lay1);
        android.view.animation.Animation an1= AnimationUtils.loadAnimation(this,R.anim.alpha);
         l.setAnimation(an1);
        an1.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                finish();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
      //  Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();


    }


}
