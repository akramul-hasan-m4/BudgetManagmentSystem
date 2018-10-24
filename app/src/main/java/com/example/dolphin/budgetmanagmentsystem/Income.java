package com.example.dolphin.budgetmanagmentsystem;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Income extends AppCompatActivity implements View.OnClickListener {

    EditText date, category, title, amount, comments;
    Button submit, back;

    com.example.dolphin.budgetmanagmentsystem.model.Income income;
    DatbaseHelper datbaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        date = findViewById(R.id.idate);
        category = findViewById(R.id.icatagory);
        title = findViewById(R.id.ititle);
        amount = findViewById(R.id.iamount);
        comments = findViewById(R.id.icomments);
        submit = findViewById(R.id.isubmit);
        back = findViewById(R.id.incomeBack);

        submit.setOnClickListener(this);
        back.setOnClickListener(this);

        income= new com.example.dolphin.budgetmanagmentsystem.model.Income();
        datbaseHelper = new DatbaseHelper(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.isubmit) {

            String idate=date.getText().toString();
            String icat=category.getText().toString();
            String ititle=title.getText().toString();
            double iamount=Double.parseDouble(amount.getText().toString());
            String icomments=comments.getText().toString();

            income.setDate(idate);
            income.setCategory(icat);
            income.setTitle(ititle);
            income.setAmount(iamount);
            income.setComments(icomments);

           long l= datbaseHelper.insertIncome(income);
           if (l>0){
               Toast.makeText(this, "Income Added", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Income.this, Income.class);
               startActivity(intent);
           }else{
               Toast.makeText(this, "Income Added failed", Toast.LENGTH_SHORT).show();
           }
        }
        if (v.getId() == R.id.incomeBack) {
            Intent intent = new Intent(Income.this, AfterLogin.class);
            startActivity(intent);
        }

    }
}
