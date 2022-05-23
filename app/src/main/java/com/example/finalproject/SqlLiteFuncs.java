package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class SqlLiteFuncs extends AppCompatActivity  {
    SQLiteDatabase db;
    MainActivity t;
    public SqlLiteFuncs (MainActivity t){
        try{
            this.t=t;
            db = t.getBaseContext().openOrCreateDatabase("Quiz.db", MODE_PRIVATE, null);
        }
        catch (Exception err){
            Toast.makeText(t,err.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void CreateTable(){
        try {
            db.execSQL("create table if not exists  Questions (Image text,Question text,Answer1 text,Answer2 text,Answer3 text,Answer4 text,Correct text)");


        }
        catch(Exception err)
        {
            Toast.makeText(t,err.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void AddToTable(){
        try {
            db.execSQL("insert into Questions values('dog.jpg','Hom Many dogs can you see in this picture ?','6 dogs','9 dogs','5 dogs','1 dog','5 dogs')");
            Toast.makeText(t, "Added ...", Toast.LENGTH_LONG).show();
        }
        catch(Exception err)
        {
            Toast.makeText(t,err.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
