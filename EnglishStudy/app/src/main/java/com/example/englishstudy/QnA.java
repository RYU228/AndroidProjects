package com.example.englishstudy;

import android.widget.CheckBox;

import java.io.Serializable;

public class QnA implements Serializable {
    private String question;
    private String answer;
    private CheckBox checkBox;

    public Boolean getCheckBox() {
        return checkBox.isChecked();
    }

    public void setCheckBox(Boolean bool) {
        checkBox.setChecked(bool);
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
