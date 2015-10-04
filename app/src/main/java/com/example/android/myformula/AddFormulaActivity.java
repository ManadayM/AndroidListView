package com.example.android.myformula;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.myformula.data.FormulaDbHelper;
import com.example.android.myformula.model.Formula;

public class AddFormulaActivity extends AppCompatActivity {

    //private LinearLayout addFormulaLayout;

    Thread thread = new Thread() {

        public void run() {
            try {
                //Intent returnIntent = new Intent();
                //setResult(RESULT_OK);
                Thread.sleep(1500);
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

        //addFormulaLayout = (LinearLayout) findViewById(R.id.layout_add_formula);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_formula, menu);
        return true;
    }

    void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }


    void saveFormula() {

        hideSoftKeyboard();

        boolean isStoredSuccessfully;

        EditText mTitle = (EditText) findViewById(R.id.title_text);
        EditText mExpression = (EditText) findViewById(R.id.formula_text);
        EditText mDescription = (EditText) findViewById(R.id.description_text);

        Formula formula = new Formula();
        formula.setTitle(mTitle.getText().toString());
        formula.setExpression(mExpression.getText().toString());
        formula.setDescription(mDescription.getText().toString());

        FormulaDbHelper dbHelper = new FormulaDbHelper(this);
        isStoredSuccessfully = dbHelper.addFormula(formula);

        //Snackbar snackbar = Snackbar.make(addFormulaLayout, isStoredSuccessfully ? "Stored successfully" : "Something went wrong", Snackbar.LENGTH_SHORT);
        //snackbar.show();

        Toast toast = Toast.makeText(this, isStoredSuccessfully ? "Stored successfully" : "Something went wrong", Toast.LENGTH_LONG);
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
}
