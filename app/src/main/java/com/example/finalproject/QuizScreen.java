package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class QuizScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz_screen);
//        ImageView QuizImage =(ImageView) findViewById(R.id.QuizImage);


//        int drawableId = this.getResources().getIdentifier(s, "drawable", getPackageName());
//        QuizImage.setImageResource(drawableId);
    }
}