package com.example.dolphin.budgetmanagmentsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.BudgetModel;

import java.util.Calendar;

public class Budget extends AppCompatActivity implements View.OnClickListener {
    Button startbtn, endbtn, submitbtn, backbtn;
    TextView startText, endText, catText, amountText;

    int sdate, smonth, syear, edate, emonth, eyear;
    DatbaseHelper datbaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        startbtn = findViewById(R.id.startDate);
        endbtn = findViewById(R.id.endDate);
        submitbtn = findViewById(R.id.budgetsubmit);
        backbtn = findViewById(R.id.beBack);

        startText = findViewById(R.id.startDateText);
        endText = findViewById(R.id.endDateText);
        catText = findViewById(R.id.bcatagory);
        amountText = findViewById(R.id.budgetamount);

        startbtn.setOnClickListener(this);
        endbtn.setOnClickListener(this);
        submitbtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);

        datbaseHelper = new DatbaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startDate) {
            Calendar calendar = Calendar.getInstance();
            sdate = calendar.get(Calendar.DAY_OF_MONTH);
            smonth = calendar.get(Calendar.MONTH);
            syear = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    startText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, sdate, smonth, syear);
            datePickerDialog.show();
        }
        if (v.getId() == R.id.endDate) {
            Calendar calendar = Calendar.getInstance();
            edate = calendar.get(Calendar.DAY_OF_MONTH);
            emonth = calendar.get(Calendar.MONTH);
            eyear = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    endText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            },syear , smonth, sdate);
            datePickerDialog.onSaveInstanceState();
            datePickerDialog.show();
        }
        if (v.getId() == R.id.budgetsubmit) {
            String cat = catText.getText().toString();
            String stDate = startText.getText().toString();
            String enDate = endText.getText().toString();
            double amountb = Double.parseDouble(amountText.getText().toString());
            BudgetModel budgetModel = new BudgetModel();

            budgetModel.setBudget_cat(cat);
            budgetModel.setBudget_start(stDate);
            budgetModel.setBudget_end(enDate);
            budgetModel.setBudget_amount(amountb);

            long status = datbaseHelper.insertbudget(budgetModel);

            if (status > 0) {
                Toast.makeText(this, "Budget Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Budget.this, Budget.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Budget Added faild", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.beBack) {
            Intent intent = new Intent(Budget.this, AfterLogin.class);
            startActivity(intent);
        }
    }
}
