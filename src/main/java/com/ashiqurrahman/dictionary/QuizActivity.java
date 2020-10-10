package com.ashiqurrahman.dictionary;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class QuizActivity extends AppCompatActivity {

    private static final String HISTORY_TABLE_NAME = "history";
    private String mAnswer;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button NextQuestionxBtn;
    private TextView mMeaningView;
    private int mQuestionNumber = 0;
    private TextView mQuestionView;
    private int mScore = 0;
    DataBaseHandler myDB;
    private ArrayList<String> quizMeanings;
    private ArrayList<String> quizWords;
    private TextView scoreCard;
     int click=0;

    class CickOnBtn1 implements View.OnClickListener {

        public void onClick(View v) {
         //   Disable_BtnAction();
            checkanwer();
            if (click == 0) {
                if (QuizActivity.this.mButtonChoice1.getText() == QuizActivity.this.mAnswer) {
                    click = 1;
                    QuizActivity.this.mScore = QuizActivity.this.mScore + 1;
                    QuizActivity.this.updateScore(QuizActivity.this.mScore);

                    if (QuizActivity.this.mQuestionNumber <= 4) {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    return;
                }
                if (QuizActivity.this.mQuestionNumber <= 4) {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    click = 1;
                }
            }

                //   QuizActivity.this.updateQuestion();

            }
        }


    class ClickOnBtn2 implements View.OnClickListener {


        public void onClick(View v) {
            checkanwer();
            Log.d("jsdk0",String.valueOf(click));

            if(click==0) {
                if (QuizActivity.this.mButtonChoice2.getText() == QuizActivity.this.mAnswer) {
                    click = 1;
                    QuizActivity.this.mScore = QuizActivity.this.mScore + 1;
                    QuizActivity.this.updateScore(QuizActivity.this.mScore);


                    //   QuizActivity.this.updateQuestion();
                    if (QuizActivity.this.mQuestionNumber <= 4) {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    return;
                }
                if (QuizActivity.this.mQuestionNumber <= 4) {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    click = 1;
                }
            }
            checkanwer();
         //   QuizActivity.this.updateQuestion();

        }
    }

    class ClickOnBtn3 implements View.OnClickListener {

        public void onClick(View v) {
            checkanwer();
            if (click == 0)
            {
                if (QuizActivity.this.mButtonChoice3.getText() == QuizActivity.this.mAnswer) {
                    click = 1;
                    QuizActivity.this.mScore = QuizActivity.this.mScore + 1;
                    QuizActivity.this.updateScore(QuizActivity.this.mScore);
                    // QuizActivity.this.updateQuestion();
                    if (QuizActivity.this.mQuestionNumber <= 4) {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                        return;
                    }
                    return;
                }
            if (QuizActivity.this.mQuestionNumber <= 4) {
                Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                click = 1;

            }
        }
            QuizActivity.this.checkanwer();

          //  QuizActivity.this.updateQuestion();

        }
    }

    class ClickOnBtn4 implements View.OnClickListener {



        public void onClick(View v) {
            checkanwer();
          if(click==0) {
              if (QuizActivity.this.mButtonChoice4.getText() == QuizActivity.this.mAnswer) {
                  click = 1;
                  QuizActivity.this.mScore = QuizActivity.this.mScore + 1;
                  QuizActivity.this.updateScore(QuizActivity.this.mScore);
                  QuizActivity.this.updateQuestion();
                  if (QuizActivity.this.mQuestionNumber <= 4) {
                      Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                      QuizActivity.this.mButtonChoice4.setBackgroundColor(Color.RED);

                      return;
                  }
                  return;
              }
              if (QuizActivity.this.mQuestionNumber <= 4) {
                  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();

              }
          }
            QuizActivity.this.checkanwer();
          //  QuizActivity.this.updateQuestion();
        }
    }
    class ClickOnBtnNextQuestion implements View.OnClickListener {


        public void onClick(View v) {
            click=0;


            QuizActivity.this.updateQuestion();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        click=0;
        setContentView(R.layout.quize_layout);
        this.NextQuestionxBtn=(Button) findViewById(R.id.next_question);
        this.scoreCard = (TextView) findViewById(R.id.Scorebord);
        this.mButtonChoice1 = (Button) findViewById(R.id.opt_1);
        this.mButtonChoice2 = (Button) findViewById(R.id.opt_2);
        this.mButtonChoice3 = (Button) findViewById(R.id.opt_3);
        this.mButtonChoice4 = (Button) findViewById(R.id.opt_4);
        this.mMeaningView = (TextView) findViewById(R.id.Question_field);
        this.myDB = new DataBaseHandler(this);
        ArrayList<Integer> allID = new DatabaseRetrieve(this).getAllID(HISTORY_TABLE_NAME);
        this.quizWords = new ArrayList();
        this.quizMeanings = new ArrayList();
        Iterator it = allID.iterator();
        while (it.hasNext()) {
            Integer indx = (Integer) it.next();
            Log.d("Myapp", indx.toString());
            Cursor cursor = this.myDB.getDbConnection_Read().rawQuery("select * from history where history_id= " + indx, null);
            if (cursor.moveToFirst()) {
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));
                this.quizWords.add(word);
                this.quizMeanings.add(meaning);
            }
            cursor.close();
        }
        updateQuestion();


        this.mButtonChoice1.setOnClickListener(new CickOnBtn1());
        this.mButtonChoice2.setOnClickListener(new ClickOnBtn2());
        this.mButtonChoice3.setOnClickListener(new ClickOnBtn3());
        this.mButtonChoice4.setOnClickListener(new ClickOnBtn4());
        this.NextQuestionxBtn.setOnClickListener(new ClickOnBtnNextQuestion());


    }

    private void updateQuestion() {
        int id1 = 0;
        int id2 = 1;
        int id3 = 2;
        int id4 = 3;
        if (this.mQuestionNumber == 0) {
            id1 = 0;
            id2 = 1;
            id3 = 2;
            id4 = 3;
        } else if (this.mQuestionNumber == 1) {
            id1 = 3;
            id2 = 1;
            id3 = 2;
            id4 = 4;
        } else if (this.mQuestionNumber == 2) {
            id1 = 1;
            id2 = 3;
            id3 = 2;
            id4 = 4;
        } else if (this.mQuestionNumber == 3) {
            id1 = 1;
            id2 = 3;
            id3 = 2;
            id4 = 4;
        } else if (this.mQuestionNumber == 4) {
            id1 = 0;
            id2 = 1;
            id3 = 2;
            id4 = 4;
        }
           this.mButtonChoice1.setEnabled(true);
            this.mButtonChoice2.setEnabled(true);
            this.mButtonChoice3.setEnabled(true);
            this.mButtonChoice4.setEnabled(true);

        this.mButtonChoice1.setText( this.quizWords.get(id1));
        this.mButtonChoice2.setText(this.quizWords.get(id2));
        this.mButtonChoice3.setText( this.quizWords.get(id3));
        this.mButtonChoice4.setText(this.quizWords.get(id4));

        if (this.mQuestionNumber <= 4) {
            this.mMeaningView.setText((CharSequence) this.quizMeanings.get(this.mQuestionNumber));
            this.mAnswer = (String) this.quizWords.get(this.mQuestionNumber);
            this.mQuestionNumber++;
        } else if (this.mQuestionNumber >= 5) {
            Toast.makeText(this, "Total Score " + this.mScore, Toast.LENGTH_SHORT).show();
        }
    }
    void checkanwer()
   {
       if (this.mAnswer.equals(this.mButtonChoice1.getText())) {
           this.mButtonChoice2.setEnabled(false);
           this.mButtonChoice3.setEnabled(false);
           this.mButtonChoice4.setEnabled(false);

       }
       if (this.mAnswer.equals(this.mButtonChoice2.getText())) {
           this.mButtonChoice1.setEnabled(false);
           this.mButtonChoice3.setEnabled(false);
           this.mButtonChoice4.setEnabled(false);

       }
       if (this.mAnswer.equals(this.mButtonChoice3.getText())) {
           this.mButtonChoice2.setEnabled(false);
           this.mButtonChoice1.setEnabled(false);
           this.mButtonChoice4.setEnabled(false);

       }
       if (this.mAnswer.equals(this.mButtonChoice4.getText())) {
           this.mButtonChoice2.setEnabled(false);
           this.mButtonChoice1.setEnabled(false);
           this.mButtonChoice3.setEnabled(false);

       }
   }
    private void updateScore(int point) {
        this.scoreCard.setText("" + point);
    }
}
