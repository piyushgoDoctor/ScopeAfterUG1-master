package com.webandrioz.scopeafterug.models;

/**
 * Created by godoctor on 27/2/17.
 */

public class Quiz {
    String questionId,ques,opA,opB,opC,opD,ans,author;

    public Quiz(String questionId, String ques, String opA, String opB, String opC, String opD, String ans, String author) {
        this.questionId = questionId;
        this.ques = ques;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.ans = ans;
        this.author = author;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String getQuestionId) {
        this.ques = getQuestionId;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
