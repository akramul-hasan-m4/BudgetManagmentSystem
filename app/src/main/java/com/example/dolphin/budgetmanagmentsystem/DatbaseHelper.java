package com.example.dolphin.budgetmanagmentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOLPHIN on 12/14/2017.
 */

public class DatbaseHelper extends SQLiteOpenHelper {
    private Context context;


    private static final String DATABASE_NAME = "bugget.db";
    private static final int VERSION = 6;


    private static final String TABLE_USER = "user";
    private static final String USER_ID = "id";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";
    private static final String USER_PHONE = "phone";
    private static final String USER_EMAIL = "email";
    private static final String USER_TBL_SQL = "CREATE TABLE " + TABLE_USER + " ( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + USER_NAME + " VARCHAR ," + USER_PASSWORD + " VARCHAR , " + USER_EMAIL + " VARCHAR , " + USER_PHONE + " INTEGER )";


    private static final String TABLE_INCOME = "income";
    private static final String INCOME_ID = "id";
    private static final String INCOME_DATE = "idate";
    private static final String INCOME_CAT = "catagory";
    private static final String INCOME_TITLE = "title";
    private static final String INCOME_AMOUNT = "amount";
    private static final String INCOME_COMMENTS = "comments";
    private static final String INCOME_TABLE_SQL = "CREATE TABLE " + TABLE_INCOME + " ( " + INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + INCOME_DATE + " VARCHAR ," + INCOME_CAT + " VARCHAR ,"
            + INCOME_TITLE + " VARCHAR ," + INCOME_AMOUNT + " DOUBLE ," + INCOME_COMMENTS + " TEXT )";

    private static final String TABLE_EXPENSE = "expense";
    private static final String EXPENSE_ID = "id";
    private static final String EXPENSE_DATE = "edate";
    private static final String EXPENSE_CAT = "catagory";
    private static final String EXPENSE_TITLE = "title";
    private static final String EXPENSE_AMOUNT = "amount";
    private static final String EXPENSE_COMMENTS = "comments";
    private static final String EXPENSE_TABLE_SQL = "CREATE TABLE " + TABLE_EXPENSE + " ( " + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + EXPENSE_DATE + " VARCHAR ," + EXPENSE_CAT + " VARCHAR ,"
            + EXPENSE_TITLE + " VARCHAR ," + EXPENSE_AMOUNT + " DOUBLE ," + EXPENSE_COMMENTS + " TEXT )";


    private static final String TABLE_BUDGET = "budget";
    private static final String BUDGET_ID = "bid";
    private static final String BUDGET_CAT = "catagory";
    private static final String BUDGET_START = "start_date";
    private static final String BUDGET_END = "end_date";
    private static final String BUDGET_AMOUNT = "amount";
    private static final String BUDGET_TABL_SQL = "CREATE TABLE " + TABLE_BUDGET + " ( " + BUDGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + BUDGET_CAT + " VARCHAR ," + BUDGET_START + " VARCHAR ,"
            + BUDGET_END + " VARCHAR ," + BUDGET_AMOUNT + " DOUBLE )";


    private static final String TABLE_BUDGET_ITEMS = "budget_items";
    private static final String BUDGET_ITEMS_ID = "id";
    private static final String BUDGET_ITEMS_NAME = "item_name";
    private static final String BUDGET_ITEMS_AMOUNT = "amount";
    private static final String BUDGET_ITEMS_SQL = "CREATE TABLE " + TABLE_BUDGET_ITEMS + " ( " + BUDGET_ITEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + BUDGET_ID + " INTEGER ," + BUDGET_ITEMS_NAME + " VARCHAR ,"
            + BUDGET_ITEMS_AMOUNT + " DOUBLE )";

    public DatbaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context, "Table is created", Toast.LENGTH_SHORT).show();
            db.execSQL(USER_TBL_SQL);
            db.execSQL(INCOME_TABLE_SQL);
            db.execSQL(EXPENSE_TABLE_SQL);
            db.execSQL(BUDGET_TABL_SQL);
            db.execSQL(BUDGET_ITEMS_SQL);
            //  Log.d("tag", "table creted");

        } catch (Exception e) {

            Toast.makeText(context, "Exception is " + e, Toast.LENGTH_LONG).show();
            Log.d("tag", "table not creted. exception is = " + e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_ITEMS);
        Toast.makeText(context, "on upgrade", Toast.LENGTH_SHORT).show();
        onCreate(db);
        //  Log.d("tag", "table up");
    }

    public long insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_PASSWORD, user.getPassword());
        contentValues.put(USER_EMAIL, user.getEmail());
        contentValues.put(USER_PHONE, user.getPhone());
        long userinsert = sqLiteDatabase.insert(TABLE_USER, null, contentValues);

        return userinsert;
    }

    public boolean login(String name, String pass) {
        boolean result = false;

        String sql = "select * from user where name ='" + name + "' AND password = '" + pass + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            return true;
        }

        return false;
    }


    public long insertIncome(com.example.dolphin.budgetmanagmentsystem.model.Income income) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INCOME_DATE, income.getDate());
        contentValues.put(INCOME_CAT, income.getCategory());
        contentValues.put(INCOME_TITLE, income.getTitle());
        contentValues.put(INCOME_AMOUNT, income.getAmount());
        contentValues.put(INCOME_COMMENTS, income.getComments());
        long userinsert = sqLiteDatabase.insert(TABLE_INCOME, null, contentValues);

        return userinsert;
    }

    public long insertExpense(ExpenseModel expenseModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_DATE, expenseModel.getDate());
        contentValues.put(EXPENSE_CAT, expenseModel.getCategory());
        contentValues.put(EXPENSE_TITLE, expenseModel.getTitle());
        contentValues.put(EXPENSE_AMOUNT, expenseModel.getAmpount());
        contentValues.put(EXPENSE_COMMENTS, expenseModel.getComments());
        long userinsert = sqLiteDatabase.insert(TABLE_EXPENSE, null, contentValues);

        return userinsert;
    }


    public double sumIncome() {

        String sql = "select SUM(amount) as total from  "+TABLE_INCOME;
        try{
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            Log.d("sum2","dhuksi = "+cursor.getDouble(cursor.getColumnIndex("total")));

            return cursor.getDouble(cursor.getColumnIndex("total"));
        }}catch (Exception e){
            Log.d("sum22","sum error = "+e.getMessage());
        }

        return 0;
    }

    public double sumExpense() {

        String sql = "select SUM(amount) as total from  "+TABLE_EXPENSE;
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                Log.d("sum2","dhuksi = "+cursor.getDouble(cursor.getColumnIndex("total")));

                return cursor.getDouble(cursor.getColumnIndex("total"));
            }}catch (Exception e){
            Log.d("sum22","sum error = "+e.getMessage());
        }

        return 0;
    }
    public double findBugetBymonth() {

        String sql = "select SUM("+BUDGET_AMOUNT+") as total from "+TABLE_BUDGET+" where month("+BUDGET_END+") = month(curdate())";
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                Log.d("sum2","dhuksi = "+cursor.getDouble(cursor.getColumnIndex("total")));

                return cursor.getDouble(cursor.getColumnIndex("total"));
            }}catch (Exception e){
            Log.d("sum22","sum error = "+e.getMessage());
        }

        return 0;
    }

    public boolean search(String email) {
        try {
            String sql = "select * from user where email = '" + email + "'";
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.getCount() > 0) {
                return true;
            }
        } catch (Exception e) {
            Log.d("tag", "error msg = " + e.getMessage());
        }
        return false;
    }

    public List<User> searchbyPhone(int phone) {
        List<User> list = new ArrayList<>();
        try {
            String sql = " select * from user where phone = " + phone;
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            if (cursor.getCount() > 0) {
                User u = new User();
                if (cursor.moveToFirst()) {
                    do {
                        u.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        u.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                        u.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                        u.setPhone(cursor.getInt(cursor.getColumnIndex("phone")));
                        u.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
                    } while (cursor.moveToNext());
                }

                list.add(u);
                return list;
            }
        } catch (Exception e) {
            Log.d("ppp", "error msg = " + e.getMessage());
        }
        return null;
    }

    public long insertbudget(BudgetModel budgetModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUDGET_CAT, budgetModel.getBudget_cat());
        contentValues.put(BUDGET_START, budgetModel.getBudget_start());
        contentValues.put(BUDGET_END, budgetModel.getBudget_end());
        contentValues.put(BUDGET_AMOUNT, budgetModel.getBudget_amount());

        long insert = sqLiteDatabase.insert(TABLE_BUDGET, null, contentValues);

        return insert;
    }
}
