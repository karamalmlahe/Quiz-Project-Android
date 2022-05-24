package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class WrongAnswersScreen extends AppCompatActivity {
    private List<WrongAnswer> WrongAnswers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answers_screen);

            getSupportActionBar().hide();
            WrongAnswers= (new QuizScreen()).getWrongAnswers();
            Toast.makeText(this,WrongAnswers.size()+"aa",Toast.LENGTH_LONG).show();

    }
}