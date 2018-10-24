package com.example.dolphin.budgetmanagmentsystem;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog.Builder builder;
    TextView addIncom, addExpense, addBudget, resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        addIncom = findViewById(R.id.addIncome);
        addExpense = findViewById(R.id.addExpense);
        addBudget = findViewById(R.id.addBudget);
        resultView = findViewById(R.id.balanceView);

        addIncom.setOnClickListener(this);
        addExpense.setOnClickListener(this);
        addBudget.setOnClickListener(this);
        resultView.setOnClickListener(this);

        resultView.setText("Total income = "+getIntent().getStringExtra("mytext")+System.getProperty("line.separator")+"Total Expense = "+getIntent().getStringExtra("myexpense")+System.getProperty("line.separator")+"---------------------------"+System.getProperty("line.separator")+"Total Balance = "+getIntent().getStringExtra("myresult"));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AfterLogin.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.addIncome) {
            Intent intent = new Intent(AfterLogin.this, Income.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.addExpense) {
            Intent intent = new Intent(AfterLogin.this, Expense.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.addBudget) {
            Intent intent = new Intent(AfterLogin.this, Budget.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.balanceView) {
            Intent intent = new Intent(AfterLogin.this, Result.class);
            startActivity(intent);
        }
    }
}
