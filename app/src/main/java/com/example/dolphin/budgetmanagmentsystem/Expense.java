package com.example.dolphin.budgetmanagmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.ExpenseModel;

public class Expense extends AppCompatActivity implements View.OnClickListener {

    EditText date, category, title, amount, comments;
    Button submit, back;

    ExpenseModel expenseModel;
    DatbaseHelper datbaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        date = findViewById(R.id.edate);
        category = findViewById(R.id.ecatagory);
        title = findViewById(R.id.etitle);
        amount = findViewById(R.id.eamount);
        comments = findViewById(R.id.ecomments);
        submit = findViewById(R.id.esubmit);
        back = findViewById(R.id.eeBack);

        submit.setOnClickListener(this);
        back.setOnClickListener(this);

        expenseModel = new ExpenseModel();
        datbaseHelper = new DatbaseHelper(this);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.esubmit) {

            String idate=date.getText().toString();
            String icat=category.getText().toString();
            String ititle=title.getText().toString();
            double iamount=Double.parseDouble(amount.getText().toString());
            String icomments=comments.getText().toString();

            expenseModel.setDate(idate);
            expenseModel.setCategory(icat);
            expenseModel.setTitle(ititle);
            expenseModel.setAmpount(iamount);
            expenseModel.setComments(icomments);

            long l= datbaseHelper.insertExpense(expenseModel);
            if (l>0){
                Toast.makeText(this, "Expense Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Expense.this, Expense.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Expense Added failed", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.eeBack) {
            Intent intent = new Intent(Expense.this, AfterLogin.class);
            startActivity(intent);
        }
    }
}
