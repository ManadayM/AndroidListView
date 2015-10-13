package com.example.android.myformula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.myformula.adapter.FormulaListAdapter;
import com.example.android.myformula.data.FormulaDbHelper;
import com.example.android.myformula.model.Formula;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TITLE_EXTRA = "com.example.android.myformula.TITLE";
    public static final String EXPRESSION_EXTRA = "com.example.android.myformula.EXPRESSION";
    private FormulaDbHelper dbHelper = new FormulaDbHelper(this);
    private List<Formula> formulaList = new ArrayList<>();
    private FormulaListAdapter adapter;

    private void loadData() {
        ListView listView = (ListView) findViewById(R.id.listview);

        formulaList = dbHelper.getAllFormulas();
        adapter = new FormulaListAdapter(this, formulaList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long rowId) {

                Formula f = (Formula) list.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
                intent.putExtra(TITLE_EXTRA, f.getTitle());
                intent.putExtra(EXPRESSION_EXTRA, f.getExpression());

                Log.d("MyFormula", "Formula '" + f.getTitle() + "' selected");

                startActivity(intent);
            }
        });
    }

    public void openAdd() {
        Intent intent = new Intent(this, AddFormulaActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                formulaList.clear();
                formulaList.addAll(dbHelper.getAllFormulas());

                adapter.notifyDataSetChanged();

                Log.d("MyFormula", "Formula stored successfully. Total count: " + Integer.toString(formulaList.size()));
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("MyFormula", "Could not store formula");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_add:
                openAdd();
                return true;

            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
