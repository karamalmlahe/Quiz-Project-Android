package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.security.AccessController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class QuizScreen extends AppCompatActivity {
    private SqlLiteFuncs SQL;
    private Question q;
    private int counter;
    private int QuestionCount;
    private CountDownTimer countDownTimer;
    private long timerLeft=40000;
    private static WrongAnswer []WrongAnswers;
    private static int i;//for WrongAnswers array


    private LinearLayout container;
    private TextView QuestionNumber;
    private ImageView QuestionImage;
    private TextView QuestionText;
    private TextView Timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz_screen);
        SQL= new SqlLiteFuncs(this);
        SQL.GetAllQuestions();
        q=SQL.getRandomQuestion();
        i=0;
        counter=0;
        QuestionCount=20    ;
        WrongAnswers=new WrongAnswer[QuestionCount];

        container =(LinearLayout) findViewById(R.id.container);

        //Question Number
        QuestionNumber=new TextView(this);
        QuestionNumber.setPadding(5,0,0,10);
        QuestionNumber.setTextSize(15);

        //Image
        QuestionImage=new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new android.view.ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,600));
        QuestionImage.setLayoutParams(params);
        QuestionImage.setScaleType(ImageView.ScaleType.FIT_XY);

        //Question
        QuestionText  = new TextView(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(new android.view.ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT));
        QuestionText.setPadding(0,25,0,25);
        QuestionText.setMinHeight(250);
        QuestionText.setTextSize(20);
        QuestionText.setGravity(Gravity.CENTER);
        QuestionText.setLayoutParams(params2);

        Timer=new TextView(this);
        Timer.setGravity(Gravity.CENTER);
        Timer.setTextSize(20);
        Timer.setLayoutParams(new LinearLayout.LayoutParams(new android.view.ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,600)));

        getQuestion();
        startTimer();


    }

    private List<String> ListOfAnswer(String Answer1,String Answer2,String Answer3,String Answer4){
        List<String> Answers= new ArrayList<String>();
        Answers.add(Answer1);
        Answers.add(Answer2);
        Answers.add(Answer3);
        Answers.add(Answer4);
        return Answers;
    }


    private void getQuestion(){
        counter++;
        //Question Number
        QuestionNumber.setText("Question "+counter+"/"+QuestionCount);
        container.addView(QuestionNumber);
        //Question Image
        if(q.GetIMGPath()!=""){
            int drawableId = this.getResources().getIdentifier(q.GetIMGPath(), "drawable", getPackageName());
            QuestionImage.setImageResource(drawableId);
            container.addView(QuestionImage);
        }

        //Question Text
        QuestionText.setText(q.GetQuestion());
        container.addView(QuestionText);

        //Answers (To random the answers
        List<String> Answers= ListOfAnswer(q.GetAnswer1(),q.GetAnswer2(),q.GetAnswer3(),q.GetAnswer4());
        Random rnd=new Random();
        while(Answers.size()>0){
            Button btn=new Button(this);
            btn.setText(Answers.remove(rnd.nextInt(Answers.size())));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b=(Button) v;
                    checkAnswer(b.getText().toString());
                    if(counter<QuestionCount){
                        container.removeAllViews();
                        getNextQuestion();
                        countDownTimer.cancel();
                        if(counter<=10){
                            timerLeft=40000;
                        }
                        else if(counter<=15){
                            timerLeft=20000;
                        }
                        else{
                            timerLeft=10000;
                        }

                        startTimer();
                    }
                    else{
                        countDownTimer.cancel();
                        saveUserScore();
                        Intent WrongAnswersScreen = new Intent(QuizScreen.this,WrongAnswersScreen.class);
                        startActivity(WrongAnswersScreen);

                    }

                }
            });
            container.addView(btn);

        }
        container.addView(Timer);


    }
    private  void checkAnswer (String UserAnswer){
        if(!UserAnswer.equals(q.GetCorrect())){
            WrongAnswers[i]=(new WrongAnswer(q.GetQuestion(),q.GetCorrect(),UserAnswer));
            i++;
        }
    }

    public void getNextQuestion(){
        q=SQL.getRandomQuestion();
        getQuestion();
    }

    public void startTimer (){
        try{
            countDownTimer=new CountDownTimer(timerLeft,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerLeft=millisUntilFinished;

                    Timer.setText(String.format("00:%02d",timerLeft/1000));
//                    Toast.makeText(QuizScreen.this,String.format("00:%02d",timerLeft/1000),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish() {
                    if(counter<QuestionCount){
                        checkAnswer("N/A");
                        container.removeAllViews();
                        getNextQuestion();
                        countDownTimer.cancel();
                        if(counter<=10){
                            timerLeft=40000;
                        }
                        else if(counter<=15){
                            timerLeft=20000;
                        }
                        else{
                            timerLeft=10000;
                        }

                        startTimer();
                    }
                    else{
                        countDownTimer.cancel();
                        saveUserScore();
                        Intent WrongAnswersScreen = new Intent(QuizScreen.this,WrongAnswersScreen.class);
                        startActivity(WrongAnswersScreen);
                    }
                }
            }.start();
        }
        catch (Exception err){
            Toast.makeText(this,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    private  void saveUserScore(){
        FileOutputStream fos=null;
        try{
            fos=openFileOutput("userScore.txt",MODE_APPEND);

            Calendar calendar= Calendar.getInstance();
            TimeZone timeZone= TimeZone.getTimeZone("Israel");
            calendar.setTimeZone(timeZone);
            int d=calendar.get(Calendar.DATE);
            int m=calendar.get(Calendar.MONTH)+1;
            int y=calendar.get(Calendar.YEAR);
            int h=calendar.get(Calendar.HOUR);
            int mi=calendar.get(Calendar.MINUTE);

            String str= d+"/"+m+"/"+y+""+" " +h+":"+mi+"|"+(QuestionCount-i)+"/"+QuestionCount+"\n";
            fos.write(str.getBytes());
            fos.close();
            Toast.makeText(this,"Score Saved",Toast.LENGTH_LONG).show();

        }
        catch(Exception err)
        {
            Toast.makeText(this,err.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static WrongAnswer[] getWrongAnswers(){
        return WrongAnswers;
    }
    public static int getNumberOfWrongAnswers(){return i;};
}