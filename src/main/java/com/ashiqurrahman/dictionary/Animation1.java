package com.ashiqurrahman.dictionary;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.*;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Animation1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        LinearLayout lin=(LinearLayout)findViewById(R.id.lin_lay);
        TextView t=(TextView)findViewById(R.id.andriodicon);
        TextView t1=(TextView)findViewById(R.id.dic);
       Typeface myface=Typeface.createFromAsset(getAssets(),"Algerian.ttf");
        t.setTypeface(myface);
        t1.setTypeface(myface);

        Animation ta= AnimationUtils.loadAnimation(this,R.anim.translate);
        Animation ta1= AnimationUtils.loadAnimation(this,R.anim.translate1);

        Animation alpha= AnimationUtils.loadAnimation(this,R.anim.alpha);
        lin.setAnimation(alpha);
        t.setAnimation(ta);
        t1.setAnimation(ta1);

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {


                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                try {
                    Thread.sleep(1700);
                    finish();
                    startActivity(in);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
