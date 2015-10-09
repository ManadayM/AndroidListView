package com.example.android.myformula;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.myformula.data.FormulaDbHelper;
import com.example.android.myformula.model.Formula;

//TODO: "/[+-\/*(){}^%!]/"

public class AddFormulaActivity extends AppCompatActivity {

    EditText mTitle, mExpression, mDescription;
    TextInputLayout mTitleLayout, mExpressionLayout, mDescriptionLayout;

    boolean isStoredSuccessfully;

    Thread thread = new Thread() {

        public void run() {
            try {

                // Signal parent activity about success/failure of db operation
                setResult(isStoredSuccessfully ? RESULT_OK : RESULT_CANCELED);

                // close this activity
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_formula);

        mTitleLayout = (TextInputLayout) findViewById(R.id.input_layout_title);
        mExpressionLayout = (TextInputLayout) findViewById(R.id.input_layout_formula);
        mDescriptionLayout = (TextInputLayout) findViewById(R.id.input_layout_description);

        mTitle = (EditText) findViewById(R.id.title_text);
        mTitle.addTextChangedListener(new textWatcher(mTitle));

        mExpression = (EditText) findViewById(R.id.formula_text);
        mExpression.addTextChangedListener(new textWatcher(mExpression));

        mDescription = (EditText) findViewById(R.id.description_text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_formula, menu);
        return true;
    }

    void setFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    private boolean validateTitle() {
        if (mTitle.getText().toString().trim().isEmpty()) {
            mTitleLayout.setError(getString(R.string.error_message_formula_title));
            setFocus(mTitle);

            return false;
        } else {
            mTitleLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateFormula() {
        if (mExpression.getText().toString().trim().isEmpty()) {
            mExpressionLayout.setError(getString(R.string.error_message_formula_expression));
            setFocus(mExpression);

            return false;
        } else {
            mExpressionLayout.setErrorEnabled(false);
        }

        return true;
    }

    void saveFormula() {

        hideSoftKeyboard();

        if (!validateTitle()) return;

        if (!validateFormula()) return;

        Formula formula = new Formula();
        formula.setTitle(mTitle.getText().toString());
        formula.setExpression(mExpression.getText().toString());
        formula.setDescription(mDescription.getText().toString());

        FormulaDbHelper dbHelper = new FormulaDbHelper(this);
        isStoredSuccessfully = dbHelper.addFormula(formula);

        //Snackbar snackbar = Snackbar.make(addFormulaLayout, isStoredSuccessfully ? "Stored successfully" : "Something went wrong", Snackbar.LENGTH_SHORT);
        //snackbar.show();

        Toast toast = Toast.makeText(this, isStoredSuccessfully ? "Stored successfully" : "Something went wrong", Toast.LENGTH_SHORT);
        toast.show();

        thread.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_discard_changes:
                finish();
                return true;

            case R.id.action_save:
                saveFormula();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Text watcher class monitors text changes on Title and Formula text boxes..
    private class textWatcher implements TextWatcher {

        private View view;

        private textWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {

                case R.id.title_text:
                    validateTitle();
                    break;

                case R.id.formula_text:
                    validateFormula();
                    break;

            }
        }
    }
}
