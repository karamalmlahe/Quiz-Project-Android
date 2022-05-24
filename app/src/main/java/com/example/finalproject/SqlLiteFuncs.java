package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqlLiteFuncs extends AppCompatActivity {
    SQLiteDatabase db;
    AppCompatActivity t;
    Integer counter;
    List<Question> Questions;
    Random rnd = new Random();

    public SqlLiteFuncs(AppCompatActivity t) {
        try {
            this.t = t;
            db = t.getBaseContext().openOrCreateDatabase("Quiz.db", MODE_PRIVATE, null);
        } catch (Exception err) {
            Toast.makeText(t, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void CreateTable() {
        try {
            db.execSQL("create table  Questions (Image text,Question text,Answer1 text,Answer2 text,Answer3 text,Answer4 text,Correct text)");
            AddToTable();

        } catch (Exception err) {
//            Toast.makeText(t, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void AddToTable() {
        try {
            db.execSQL("insert into Questions values('dog','Hom Many dogs can you see in this picture ?','6 dogs','9 dogs','5 dogs','1 dog','5 dogs')");
            db.execSQL("insert into Questions values('monalisa','What is the name of this famous painting?','Guernica','The Girl With A Pearl Earring','Mona Lisa','The Scream','Mona Lisa')");
            db.execSQL("insert into Questions values('cats','How many cats can you see in this picture?','5 cats','10 cats','12 cats','7 cats','10 cats')");
            db.execSQL("insert into Questions values('thomasedison','Who was the inventor of the light bulb?','Thomas Edison','Albert Einstein','Johannes Gutenberg','Grace Hopper','Thomas Edison')");
            db.execSQL("insert into Questions values('messi','Identify the football player.','Cristiano Ronaldo','Mohamed Salah','Karim Benzema','Messi','Messi')");
            db.execSQL("insert into Questions values('elephant','Identify the animal','Dog','Elephant','Chicken','Cow','Elephant')");
            db.execSQL("insert into Questions values('eiffel','In which country is the Eiffel Tower located?','Italy','Indonesia','United States','France','France')");
            db.execSQL("insert into Questions values('bmw','What is the name of this automotive brand?','Skoda','Citroen','BMW','Mersedes','BMW')");
            db.execSQL("insert into Questions values('burjkhalifa','How high is the Burj Khalifa in Dubai?','Around 830 metres','Around 960 metres','Around 750 metres','Around 1070 metres','Around 830 metres')");
            db.execSQL("insert into Questions values('koreanfont','What is the name of this language?','Japanese','Chinese','Arabic','Korean','Korean')");
            db.execSQL("insert into Questions values('btc','What is the name of this cryptocurrency?','Ethereum','Bitcoin','XRP','Dogcoin','Bitcoin')");
            db.execSQL("insert into Questions values('usa','What country flag is this?','Germany','Austria','United States','Russia','United States')");
            db.execSQL("insert into Questions values('tesla','What is the name of this automotive brand?','BMW','Tesla','Nissan','Hyundai','Tesla')");
            db.execSQL("insert into Questions values('ronaldo','Identify the football player.','Ronaldo','Messi','Neymar','Ramos','Ronaldo')");
            db.execSQL("insert into Questions values('tiger','Identify the animal','Donkey','Tiger','Mouse','Lion','Tiger')");
            db.execSQL("insert into Questions values('sun','What is the color of a sun?','Pink','Blue','Yellow','White','Yellow')");
            db.execSQL("insert into Questions values('englishletters','How many letters are there in the English?','20 letters','10 letters','18 letters','26 letters','26 letters')");
            db.execSQL("insert into Questions values('rainbow','How many colors are there in a rainbow?','10','12','7','8','7')");
            db.execSQL("insert into Questions values('numbers','Which number comes after 2?','4','1','3','7','3')");
            db.execSQL("insert into Questions values('apple','What is the name of this fruit?','Mango','Banana','Orange','Apple','Apple')");

            db.execSQL("insert into Questions values('','There are ________ days in a year.','316','365','360','325','365')");
            db.execSQL("insert into Questions values('','There are ________ months in a year.','6','9','12','8','12')");
            db.execSQL("insert into Questions values('','How many hours are there in a day?','48 hours','24 hours','72 hours','12 hours','24 hours')");
            db.execSQL("insert into Questions values('','How many fingers are there in a human body’s single hand?','10 fingers','12 fingers','15 fingers','5 fingers','5 fingers')");
            db.execSQL("insert into Questions values('','How many letters are there in the English?','30 letters','26 letters','18 letters','10 letters','26 letters')");
            db.execSQL("insert into Questions values('','What is the color of a sun?','Yellow','White','Pink','Blue','Yellow')");
            db.execSQL("insert into Questions values('','What is 2+7?','1','9','4','5','9')");
            db.execSQL("insert into Questions values('','Which number comes after 6?','4','5','7','8','7')");
            db.execSQL("insert into Questions values('','How many colors are there in a rainbow?','10','12','7','8','7')");
            db.execSQL("insert into Questions values('','Which day comes after Friday?','Thursday','Monday','Saturday','Sunday','Saturday')");
            db.execSQL("insert into Questions values('','What is 2+2?','10','12','4','8','4')");
            db.execSQL("insert into Questions values('','Finish the sequence: 9, 18, 27, __','32','28','36','0','36')");
            db.execSQL("insert into Questions values('','What is the 15th letter of the English alphabet?','F','I','O','M','O')");




            GetAllQuestions();
            Toast.makeText(t, "Added ...", Toast.LENGTH_LONG).show();
        } catch (Exception err) {
            Toast.makeText(t, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void GetAllQuestions() {
        Cursor q = db.rawQuery("select * from Questions ", null);
        Questions = new ArrayList<Question>();
        while (q.moveToNext()) {
            String img = q.getString(0);
            String Question = q.getString(1);
            String Answer1 = q.getString(2);
            String Answer2 = q.getString(3);
            String Answer3 = q.getString(4);
            String Answer4 = q.getString(5);
            String Correct = q.getString(6);
            Questions.add(new Question(img, Question, Answer1, Answer2, Answer3, Answer4, Correct));
        }
        q.close();
    }

    public Question getRandomQuestion()
    {
        if(Questions.size()>0){
            Question q = Questions.remove(rnd.nextInt(Questions.size()));
            return q;
        }
        return null;

    }
}
