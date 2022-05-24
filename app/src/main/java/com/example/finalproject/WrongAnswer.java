package com.example.finalproject;

public class WrongAnswer {
    private String Question;
    private String CorrectAnswer;
    private String UserAnswer;

    public WrongAnswer(String Q,String C,String U){
        this.Question=Q;
        this.CorrectAnswer=C;
        this.UserAnswer=U;
    }

    public String getQuestion() {
        return Question;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public String getUserAnswer() {
        return UserAnswer;
    }
}
