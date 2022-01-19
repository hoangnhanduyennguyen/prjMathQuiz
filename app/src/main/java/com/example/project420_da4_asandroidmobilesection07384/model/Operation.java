package com.example.project420_da4_asandroidmobilesection07384.model;

import com.example.project420_da4_asandroidmobilesection07384.R;

public class Operation {

    private int firstOperand;
    private char operator;
    private int secondOperand;
    private double result;
    private double answer;

    public Operation(int firstOperand, char operator, int secondOperand, double answer) {
        this.firstOperand = firstOperand;
        this.operator = operator;
        this.secondOperand = secondOperand;
        this.result = getResult();
        this.answer = answer;
    }

    public int getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public char getOperator() { return operator; }

    public void setOperator(char operator) { this.operator = operator; }

    public int getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(int secondOperand) {
        this.secondOperand = secondOperand;
    }

    public double getResult() {
        switch (this.operator){
            case '+':
               return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                if (secondOperand != 0) return Math.round(((double)firstOperand / (double)secondOperand) * 100.0) / 100.0;
        }
        return R.string.msgError;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getAnswer() { return answer; }

    public void setAnswer(double answer) { this.answer = answer; }

    @Override
    public String toString() {
        return  firstOperand +
                " " + operator +
                " " + secondOperand;
    }
}
