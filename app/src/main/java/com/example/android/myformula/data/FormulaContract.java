package com.example.android.myformula.data;

import android.provider.BaseColumns;

public class FormulaContract {

    public static abstract class FormulaEntry implements BaseColumns {

        public static final String TABLE_NAME = "formula";

        public static final String COLUMN_FORMULA_TITLE = "title";

        public static final String COLUMN_FORMULA_DESC = "description";

        public static final String COLUMN_FORMULA_EXPRESSION = "expression";
    }
}
