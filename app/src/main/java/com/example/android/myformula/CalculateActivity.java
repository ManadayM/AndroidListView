package com.example.android.myformula;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

// (/(?:\d*\.)?\d+|[+-/*(){}^%!]|tan|log|sin|sqrt|qbrt/gi,"")

public class CalculateActivity extends AppCompatActivity {

    private String mTitle;
    private String mExpresssion;
    private String mOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mTitle = getIntent().getStringExtra(MainActivity.TITLE_EXTRA);
        mExpresssion = getIntent().getStringExtra(MainActivity.EXPRESSION_EXTRA);
        //mOperators = mExpresssion.replaceAll("[(?:\\d*\\.)?\\d+|[+-/*(){}^%!]|tan|log|sin|sqrt|qbrt]", " ").trim();


        CalculateActivity.this.setTitle(mTitle);

        Log.d("Calculate", mOperators);
    }
}
