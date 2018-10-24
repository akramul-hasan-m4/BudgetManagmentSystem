package com.example.dolphin.budgetmanagmentsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button login, signup, forgetClick;
    private TextView forgetMSg, balanceView;
    private AlertDialog.Builder builder;

    DatbaseHelper datbaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datbaseHelper = new DatbaseHelper(this);
        user = new User();
        SQLiteDatabase sqLiteDatabase = datbaseHelper.getWritableDatabase();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        forgetClick = findViewById(R.id.redbtn);
        forgetMSg = findViewById(R.id.hiddenmsg);
        balanceView = findViewById(R.id.balanceView);

        login.setOnClickListener(this);
        forgetClick.setOnClickListener(this);
        signup.setOnClickListener(this);

        forgetClick.setVisibility(View.INVISIBLE);
        forgetMSg.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login) {

            Intent intent = new Intent(MainActivity.this, AfterLogin.class);
            String userN = username.getText().toString();
            String pass = password.getText().toString();

            boolean status = datbaseHelper.login(userN, pass);
            if (status == true) {
                try {
                    double d = datbaseHelper.sumIncome();
                    double expense = datbaseHelper.sumExpense();
                    double result=d-expense;
                  //  double budget = datbaseHelper.findBugetBymonth();

                    intent.putExtra("mytext",String.valueOf(d));
                    intent.putExtra("myexpense",String.valueOf(expense));
                    intent.putExtra("myresult",String.valueOf(result));
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("summm", "m error = " + e.getMessage());
                }
            } else {
                Toast.makeText(this, "Login Failed ", Toast.LENGTH_SHORT).show();
                forgetClick.setVisibility(View.VISIBLE);
                forgetMSg.setVisibility(View.VISIBLE);
            }
        }

        if (v.getId() == R.id.signup) {
            Intent intent = new Intent(MainActivity.this, SingUp.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.redbtn) {
            Intent intent = new Intent(MainActivity.this, ForgetPass.class);
            startActivity(intent);
        }
    }


    @Override
    public void onBackPressed() {
        //initialize alrt dialoge builder
        builder = new AlertDialog.Builder(MainActivity.this);

//                set Alert title
        builder.setTitle(R.string.title_text);

//                set msg
        builder.setMessage(R.string.dialog_msg_text);

//                user jate dialoge er side e click na korte pare
        builder.setCancelable(false);

        builder.setIcon(R.drawable.danger);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                ;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
