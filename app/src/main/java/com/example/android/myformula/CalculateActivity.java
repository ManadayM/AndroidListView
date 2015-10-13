package com.example.android.myformula;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// (/(?:\d*\.)?\d+|[+-/*(){}^%!]|tan|log|sin|sqrt|qbrt/gi,"")

public class CalculateActivity extends AppCompatActivity {

    private String mTitle;
    private String mExpresssion;
    private String[] mOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mTitle = getIntent().getStringExtra(MainActivity.TITLE_EXTRA);
        mExpresssion = getIntent().getStringExtra(MainActivity.EXPRESSION_EXTRA);
        mOperators = mExpresssion.replaceAll("(?i)(?:\\d*\\.)?\\d+|[+-/^*()]|tan|log|sin|sqrt|\\s+", " ").trim().replaceAll("\\s{2,}", " ").split(" ");

        TextView formulaText = (TextView) findViewById(R.id.formula_text);
        formulaText.setText(mExpresssion);

        // Set activity title
        CalculateActivity.this.setTitle(mTitle);

        Set<String> set = new HashSet<>(Arrays.asList(mOperators));
        String[] mUniqueOperators = new String[set.size()];
        set.toArray(mUniqueOperators);

        Log.d("CalculateActivity", "Unique operators >" + Arrays.toString(mUniqueOperators) + "<");

        LinearLayout container = (LinearLayout) findViewById(R.id.linear_layout);

        for (int i = 0; i < mUniqueOperators.length; i++) {

            TextInputLayout textInput = new TextInputLayout(this);

            LinearLayout.LayoutParams textInputParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textInputParams.setMargins(8, 8, 8, 8);

            container.addView(textInput, textInputParams);

            EditText editText = new EditText(this);
            editText.setSingleLine(true);
            editText.setHint(mUniqueOperators[i].toUpperCase());
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            ViewGroup.LayoutParams editTextParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textInput.addView(editText, editTextParams);
        }


    }
}
