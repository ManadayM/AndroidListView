package com.example.android.myformula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.myformula.adapter.FormulaListAdapter;
import com.example.android.myformula.model.Formula;

import java.util.ArrayList;

/**
 * http://stackoverflow.com/questions/11945563/how-listviews-recycling-mechanism-works
 * http://stackoverflow.com/questions/19469073/how-do-you-efficiently-load-bitmaps-from-drawable-folder-into-a-listview/19469076#19469076
 * http://www.pcsalt.com/android/listview-using-baseadapter-android/
 * http://www.androidhive.info/2014/07/android-custom-listview-with-image-and-text-using-volley/
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<Formula> formulaList = new ArrayList<>();
    private ListView listView;
    private FormulaListAdapter adapter;

    private void populateList() {

        for (int i = 0; i < 20; i++)
            formulaList.add(new Formula("Formula - " + (i + 1), "Formula brief description will go here.."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateList();

        listView = (ListView) findViewById(R.id.listview);
        adapter = new FormulaListAdapter(this, formulaList);
        listView.setAdapter(adapter);
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

    public void openAdd() {
        Intent intent = new Intent(this, AddFormulaActivity.class);
        startActivity(intent);
    }
}
