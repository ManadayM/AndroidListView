package com.example.android.myformula;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.myformula.data.FormulaDbHelper;
import com.example.android.myformula.model.Formula;

public class AddFormulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_formula);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_formula, menu);
        return true;
    }

    void saveFormula() {

        EditText mTitle = (EditText) findViewById(R.id.title_text);
        EditText mExpression = (EditText) findViewById(R.id.formula_text);
        EditText mDescription = (EditText) findViewById(R.id.description_text);

        Formula formula = new Formula();
        formula.setTitle(mTitle.getText().toString());
        formula.setExpression(mExpression.getText().toString());
        formula.setDescription(mDescription.getText().toString());

        FormulaDbHelper dbHelper = new FormulaDbHelper(this);
        dbHelper.addFormula(formula);
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
