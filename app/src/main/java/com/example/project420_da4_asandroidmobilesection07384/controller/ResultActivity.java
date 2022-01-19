package com.example.project420_da4_asandroidmobilesection07384.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project420_da4_asandroidmobilesection07384.R;

public class ResultActivity extends AppCompatActivity {

    TextView txtShowAll;
    Button btnGoBack;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize(){
        txtShowAll = (TextView) findViewById(R.id.txtShowAll);
        Intent intent = getIntent();
        txtShowAll.setText(intent.getStringExtra("showAll"));
        btnGoBack = (Button) findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
