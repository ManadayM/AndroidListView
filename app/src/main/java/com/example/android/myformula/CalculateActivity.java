package com.example.android.myformula;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
    private String mExpression;
    private String[] mOperators;

    private TextView mFormulaText;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mFormulaText = (TextView) findViewById(R.id.formula_text);

        mTitle = getIntent().getStringExtra(MainActivity.TITLE_EXTRA);
        mExpression = getIntent().getStringExtra(MainActivity.EXPRESSION_EXTRA);
        mOperators = mExpression.replaceAll("(?i)(?:\\d*\\.)?\\d+|[+-/^*()]|tan|log|sin|sqrt|\\s+", " ").trim().replaceAll("\\s{2,}", " ").split(" ");

        TextView formulaText = (TextView) findViewById(R.id.formula_text);
        formulaText.setText(mExpression);

        // Set activity title
        CalculateActivity.this.setTitle(mTitle);

        Set<String> set = new HashSet<>(Arrays.asList(mOperators));
        String[] mUniqueOperators = new String[set.size()];
        set.toArray(mUniqueOperators);

        Log.d("CalculateActivity", "Unique operators >" + Arrays.toString(mUniqueOperators) + "<");

        container = (LinearLayout) findViewById(R.id.linear_layout);

        for (int i = 0; i < mUniqueOperators.length; i++) {

            TextInputLayout textInput = new TextInputLayout(this);
            textInput.setId(i);

            LinearLayout.LayoutParams textInputParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textInputParams.setMargins(8, 8, 8, 8);

            container.addView(textInput, textInputParams);

            EditText editText = new EditText(this);
            editText.setId(1000 + i);
            editText.setSingleLine(true);
            editText.setHint(mUniqueOperators[i].toUpperCase());
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            ViewGroup.LayoutParams editTextParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textInput.addView(editText, editTextParams);

            editText.addTextChangedListener(new textWatcher(i));
        }
    }

    private class textWatcher implements TextWatcher {

        private int resId;

        private textWatcher(int resId) {
            this.resId = resId;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d("TextWatcher", "beforeTextChanged called");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("TextWatcher", "onTextChanged called");

        }

        @Override
        public void afterTextChanged(Editable s) {

            EditText et = (EditText) container.findViewById(1000 + resId);
            String hint = (String) et.getHint();


            // Log.d("TextWatcher", "afterTextChanged called " + s.toString() + " " + );

            //Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");

            //wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            //TV.setText(wordtoSpan);
        }
    }
}
