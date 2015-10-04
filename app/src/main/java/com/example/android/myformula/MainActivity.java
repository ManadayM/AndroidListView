package com.example.android.myformula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.myformula.adapter.FormulaListAdapter;
import com.example.android.myformula.data.FormulaDbHelper;
import com.example.android.myformula.model.Formula;

import java.util.ArrayList;
import java.util.List;

/**
 * http://stackoverflow.com/questions/11945563/how-listviews-recycling-mechanism-works
 * http://stackoverflow.com/questions/19469073/how-do-you-efficiently-load-bitmaps-from-drawable-folder-into-a-listview/19469076#19469076
 * http://www.pcsalt.com/android/listview-using-baseadapter-android/
 * http://www.androidhive.info/2014/07/android-custom-listview-with-image-and-text-using-volley/
 */

public class MainActivity extends AppCompatActivity {

    FormulaDbHelper dbHelper = new FormulaDbHelper(this);
    private List<Formula> formulaList = new ArrayList<>();
    private ListView listView;
    private FormulaListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formulaList = dbHelper.getAllFormulas();

        listView = (ListView) findViewById(R.id.listview);
        adapter = new FormulaListAdapter(this, formulaList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyFormula", "onResume called");
        adapter.notifyDataSetChanged();
        //formulaList = dbHelper.getAllFormulas();
        Log.d("MyFormula", "List count: " + Integer.toString(formulaList.size()));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        //adapter.notifyDataSetChanged();
        Log.d("MyFormula", "onRestart called");
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){

            if(resultCode == RESULT_OK){
                Log.d("MyFormula", "onActivityResult_RESULT_OK called");
                formulaList = dbHelper.getAllFormulas();
                adapter.notifyDataSetChanged();
            }
        }
    }*/

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
        //startActivityForResult(intent, 1);
    }
}
