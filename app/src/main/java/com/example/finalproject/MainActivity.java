package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SqlLiteFuncs SQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        SQL= new SqlLiteFuncs(this);
        SQL.CreateTable();

    }
    public void startQuiz(View view){
//        SQL.AddToTable();
        Intent QuizScreen = new Intent(this, QuizScreen.class);
        startActivity(QuizScreen);

    }
}