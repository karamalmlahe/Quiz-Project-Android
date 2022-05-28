package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WrongAnswersScreen extends AppCompatActivity {
    private WrongAnswer[] WrongAnswers;
    private int NumberOfWrongAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answers_screen);
            getSupportActionBar().hide();
            WrongAnswers= QuizScreen.getWrongAnswers();
            NumberOfWrongAnswer=QuizScreen.getNumberOfWrongAnswers();

        RelativeLayout container =(RelativeLayout) findViewById(R.id.containerr);

        TextView Title= findViewById(R.id.Title);
        if(4<NumberOfWrongAnswer){
            Title.setText("FAILED !");
            Title.setTextColor(Color.RED);
        }
        else{
            Title.setText("Good !");
            Title.setTextColor(Color.GREEN);
        }

        TextView CountWrongAnswers=findViewById(R.id.CountWrongAnswers);
        CountWrongAnswers.setText("You answered "+(WrongAnswers.length-NumberOfWrongAnswer+"/"+WrongAnswers.length)+" questions correctly");

        LinearLayout scrollView = (LinearLayout) findViewById(R.id.scrollView);

        for(int i=0;i<NumberOfWrongAnswer;i++){

            TextView question=new TextView(this);
            question.setText(i+1+") "+WrongAnswers[i].getQuestion());
            question.setPadding(0,15,0,15);
            scrollView.addView(question);

            TextView UserAnswer=new TextView(this);
            UserAnswer.setText("Your Choice: "+WrongAnswers[i].getUserAnswer());
            UserAnswer.setPadding(0,15,0,15);
            UserAnswer.setTextColor(Color.RED);
            scrollView.addView(UserAnswer);

            TextView CorrectAnswer=new TextView(this);
            CorrectAnswer.setText("Correct Answer: "+WrongAnswers[i].getCorrectAnswer());
            CorrectAnswer.setPadding(0,15,0,15);
            CorrectAnswer.setTextColor(Color.GREEN);
            scrollView.addView(CorrectAnswer);

            TextView Line=new TextView(this);
            Line.setBackgroundColor(Color.BLACK);
            Line.setHeight(2);
            scrollView.addView(Line);

        }



    }

    public void GoHomePage(View view) {
        Intent Home = new Intent(this,MainActivity.class);
        startActivity(Home);
    }
}