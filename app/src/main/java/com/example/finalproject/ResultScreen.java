package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_result_screen);



        TableLayout result=(TableLayout)  findViewById(R.id.ResultTable);



        int n=1;
        FileInputStream fin=null;
        String str="";
        BufferedReader br=null;
        try{
            fin=openFileInput("userScore.txt");
            br=new BufferedReader(new InputStreamReader(fin));
            String line=null;
            while(( line=br.readLine())!=null)
            {
                int getIndex =line.indexOf("|");
                String date=line.substring(0,getIndex);
                String Score=line.substring(getIndex+1);

                TextView dateText= new TextView(this);
                dateText.setText(date);
                dateText.setGravity(Gravity.CENTER);
                dateText.setPadding(0,15,0,15);

                TextView scoreText= new TextView(this);
                scoreText.setText(Score);
                scoreText.setGravity(Gravity.CENTER);
                scoreText.setPadding(0,15,0,15);

                TableRow row=new TableRow(this);
                row.addView(dateText );
                row.addView(scoreText );
                result.addView(row);

                n++;
            }

            fin.close();
        }
        catch(Exception err)
        {
            Toast.makeText(this,err.getMessage(), Toast.LENGTH_SHORT).show();
        }


//        try{
//            FileInputStream fin=openFileInput("userScore.txt");
//            byte[]bytes=new byte[fin.available()];
//            fin.read(bytes);
//            String date=(new String(bytes)).split("|")[0];
//            String Score=(new String(bytes)).split("|")[1];
//
//            TextView dateText= new TextView(this);
//            dateText.setText(date);
//            dateText.setGravity(Gravity.FILL);
//
//            TextView scoreText= new TextView(this);
//            scoreText.setText(Score);
//            scoreText.setGravity(Gravity.FILL);
//
//
//            result.addView(dateText );
//            result.addView(scoreText );
//
//
//            fin.close();
//        }
//        catch(Exception err)
//        {
//            Toast.makeText(this,err.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }
}