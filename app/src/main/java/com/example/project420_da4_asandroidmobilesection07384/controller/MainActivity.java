package com.example.project420_da4_asandroidmobilesection07384.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project420_da4_asandroidmobilesection07384.R;
import com.example.project420_da4_asandroidmobilesection07384.model.Operation;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TableLayout tableRow;
    TextView txtOperation;
    EditText edtAnswer;
    ArrayList<Operation> operations;
    Operation operation;
    Random r;
    String showAll;
    double percentCorrect;
    int min, max, countCorrect;
    char[] operators = {'+', '-', '*', '/'};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        operations = new ArrayList<>();
        r = new Random();
        min = 0;
        max = 100;
        txtOperation = (TextView) findViewById(R.id.txtOperation);
        edtAnswer = (EditText) findViewById(R.id.editAnswer);
        tableRow = (TableLayout) findViewById(R.id.table);
        createOnClickListener();
    }

    private void createOnClickListener(){
        for (int i = 0; i < tableRow.getChildCount(); i++){
            TableRow row = (TableRow) tableRow.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j ++){
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(view -> {
                    String answer = edtAnswer.getText().toString();
                    switch (view.getId()){
                        case (R.id.btnGenerate): {
                            btnGenerate();
                            break;
                        }
                        case (R.id.btnEqual): {
                            btnEqual();
                            break;
                        }
                        case (R.id.btnShowAll): {
                            btnShowAll();
                            break;
                        }
                        case (R.id.btnClear): {
                            edtAnswer.setText("");
                            break;
                        }
                        case (R.id.btnQuit): {
                            btnQuit();
                            break;
                        }
                        case (R.id.btnZero): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnZero)));
                            break;
                        }
                        case (R.id.btnOne): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnOne)));
                            break;
                        }
                        case (R.id.btnTwo): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnTwo)));
                            break;
                        }
                        case (R.id.btnThree): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnThree)));
                            break;
                        }
                        case (R.id.btnFour): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnFour)));
                            break;
                        }
                        case (R.id.btnFive): {
                            edtAnswer.setText(String.format("%s%s", answer,getText(R.string.btnFive)));
                            break;
                        }
                        case (R.id.btnSix): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnSix)));
                            break;
                        }
                        case (R.id.btnSeven): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnSeven)));
                            break;
                        }
                        case (R.id.btnEight): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnEight)));
                            break;
                        }
                        case (R.id.btnNine): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnNine)));
                            break;
                        }
                        case (R.id.btnPoint): {
                            edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnPoint)));
                            break;
                        }
                        case (R.id.btnMinus): {
                            //make sure user do not enter something like 7 - 3 because this is not a number
                            if(!edtAnswer.getText().toString().trim().isEmpty()){
                                Toast.makeText(MainActivity.this, getText(R.string.msgInvalidNumber), Toast.LENGTH_SHORT).show();
                            }else{
                                edtAnswer.setText(String.format("%s%s", answer, getText(R.string.btnMinus)));
                            }
                            break;
                        }
                    }
                });
            }
        }
    }

    private void btnClearAll(){
        txtOperation.setText("");
        edtAnswer.setText("");
    }

    private void btnGenerate(){
        edtAnswer.setText("");
        int firstOperand = (min + r.nextInt(max - min + 1));
        char operator = operators[r.nextInt(operators.length)];
        int secondOperand = (min + r.nextInt(max - min + 1));
        int answer = (R.string.msgEmpty);
        //if falling in the / by 0, change the operator
        if (operator == '/' && secondOperand == 0){
            operator = operators[r.nextInt(operators.length - 1)];
        }
        operation = new Operation(firstOperand,operator,secondOperand,answer);
        txtOperation.setText(operation.toString());
        operations.add(operation);
    }

    private void btnEqual(){
        if (operations.size() == 0 || txtOperation.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, getText(R.string.msgNotEqual), Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                double answer = Double.parseDouble(edtAnswer.getText().toString());
                operation.setAnswer(answer);
            }catch (Exception e){
                // if the user enters an invalid number, eg. 7 - 2
                Toast.makeText(MainActivity.this, getText(R.string.msgEmpty), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        btnClearAll();
    }

    private void btnShowAll(){
        if (operations.size() == 0){
            Toast.makeText(MainActivity.this, getText(R.string.msgNotEqual), Toast.LENGTH_SHORT).show();
        }else{
            showAll = "";
            countCorrect = 0;
            String answer, result;
            for (int i = 0; i < operations.size(); i++){
                Operation operation = operations.get(i);
                showAll += operation.toString() + " = " ;
                //this is to print int or double
                if (operation.getOperator() != '/'){
                    answer = String.valueOf((int)operation.getAnswer());
                    result = String.valueOf((int)operation.getResult());
                }else{
                    answer =  String.valueOf((double)operation.getAnswer());
                    result = String.valueOf((double) operation.getResult());
                }
                //whether the user's answer was correct, incorrect or they didn't answer by leaving the editText empty or not clicking on equal button
                if (operation.getAnswer() == R.string.msgEmpty){
                    showAll += "?" + "\n" + getText(R.string.msgEmpty) +"\n" + getText(R.string.msgAnswer)+ " " + result;
                }
                else if ((double) operation.getResult() == (double) operation.getAnswer()){
                    countCorrect += 1;
                    showAll += answer +"\n" + getText(R.string.msgCorrect);
                }
                else{
                    showAll += answer + "\n" + getText(R.string.msgWrong) +"\n" + getText(R.string.msgAnswer)+ " " + result;
                }
                showAll += "\n" + getText(R.string.msgDash) + "\n";
            }
            if (showAll.trim().length() != 0){
                edtAnswer.setText("");
                showAll += "\n";
                percentCorrect = Math.round((((double)countCorrect / operations.size())* 100) * 100.0) / 100.0;
                if(percentCorrect % 1 == 0){
                    showAll += String.valueOf((int)percentCorrect) + getText(R.string.percentCorrect) + "\n" +(100 - (int)(percentCorrect));
                }else{
                    showAll += String.valueOf(percentCorrect) + getText(R.string.percentCorrect) + "\n" + (100.0 - (percentCorrect));
                }
                showAll +=  getText(R.string.percentWrong);
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("showAll",showAll);
                startActivity(intent);
            }
        }
        btnClearAll();
    }

    private void btnQuit(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle(R.string.msgQuitTitle)
                //set message
                .setMessage(R.string.msgQuitMsg)
                //set positive button
                .setPositiveButton(getText(R.string.msgPositiveButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(MainActivity.this,getText(R.string.msgQuitYesReturn),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                //set negative button
                .setNegativeButton(getText(R.string.msgNegativeButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(MainActivity.this,getText(R.string.msgQuitNoReturn),Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}