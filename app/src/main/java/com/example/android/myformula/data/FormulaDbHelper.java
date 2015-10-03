package com.example.android.myformula.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.myformula.data.FormulaContract.FormulaEntry;
import com.example.android.myformula.model.Formula;

import java.util.ArrayList;
import java.util.List;

public class FormulaDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "formula.db";

    private String formulaEntryAllColumns[] = new String[]{
            FormulaEntry.COLUMN_FORMULA_ID,
            FormulaEntry.COLUMN_FORMULA_TITLE,
            FormulaEntry.COLUMN_FORMULA_EXPRESSION,
            FormulaEntry.COLUMN_FORMULA_DESC
    };

    public FormulaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FORMULA_TABLE = "CREATE TABLE " + FormulaEntry.TABLE_NAME + " (" +
                FormulaEntry._ID + " INTEGER PRIMARY KEY, " +
                FormulaEntry.COLUMN_FORMULA_TITLE + " TEXT NOT NULL, " +
                FormulaEntry.COLUMN_FORMULA_EXPRESSION + " TEXT NOT NULL, " +
                FormulaEntry.COLUMN_FORMULA_DESC + " TEXT);";

        db.execSQL(SQL_CREATE_FORMULA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addFormula(Formula formula) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FormulaEntry.COLUMN_FORMULA_TITLE, formula.getTitle());
        values.put(FormulaEntry.COLUMN_FORMULA_EXPRESSION, formula.getExpression());
        values.put(FormulaEntry.COLUMN_FORMULA_DESC, formula.getDescription());

        db.insert(FormulaEntry.TABLE_NAME, null, values);

        values.clear();
        db.close();
    }

    public Formula getFormula(int id) {

        Formula formula = null;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(FormulaEntry.TABLE_NAME, formulaEntryAllColumns, FormulaEntry.COLUMN_FORMULA_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {

            formula = new Formula(
                    cursor.getInt(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_ID)),
                    cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_TITLE)),
                    cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_EXPRESSION)),
                    cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_DESC)));
        }

        cursor.close();
        db.close();

        return formula;
    }

    public List<Formula> getAllFormulas() {

        List<Formula> formulaList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + FormulaEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Formula formula = new Formula();

                formula.setId(cursor.getInt(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_ID)));
                formula.setTitle(cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_TITLE)));
                formula.setExpression(cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_EXPRESSION)));
                formula.setExpression(cursor.getString(cursor.getColumnIndex(FormulaEntry.COLUMN_FORMULA_DESC)));

                formulaList.add(formula);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return formulaList;
    }

}