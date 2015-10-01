package com.example.android.myformula.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.myformula.data.FormulaContract.FormulaEntry;

/**
 * Created by manaday on 25/09/15.
 */
public class FormulaDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "formula.db";

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
}