package com.example.finalproject;

public class Question {
    private String IMGPath;
    private String Question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private String Correct;
    public Question(String img ,String Question, String Answer1, String Answer2, String Answer3, String Answer4, String Correct)
    {
        this.IMGPath = img;
        this.Question = Question;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.Answer4 = Answer4;
        this.Correct = Correct;
    }
    public String GetIMGPath()
    {
        return this.IMGPath;
    }
    public String GetQuestion()
    {
        return this.Question;
    }
    public String GetAnswer1()
    {
        return this.Answer1;
    }
    public String GetAnswer2()
    {
        return this.Answer2;
    }
    public String GetAnswer3()
    {
        return this.Answer3;
    }
    public String GetAnswer4()
    {
        return this.Answer4;
    }
    public String GetCorrect()
    {
        return this.Correct;
    }
}
