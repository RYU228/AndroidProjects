package com.example.englishstudy;

import android.widget.CheckBox;

public class QnA {
    private String question;
    private String answer;
    private CheckBox checkBox;

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public QnA(String question, String answer, CheckBox checkBox) {
        this.question = question;
        this.answer = answer;
        this.checkBox = checkBox;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
