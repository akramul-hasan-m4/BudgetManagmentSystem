package com.example.dolphin.budgetmanagmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.User;

public class SingUp extends AppCompatActivity implements View.OnClickListener {
    EditText name, password, email, phone;
    Button submit, back;
    DatbaseHelper datbaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        name = findViewById(R.id.singName);
        password = findViewById(R.id.singPassword);
        email = findViewById(R.id.singEmail);
        phone = findViewById(R.id.singPhone);

        submit = findViewById(R.id.signId);
        back = findViewById(R.id.signBack);

        datbaseHelper=new DatbaseHelper(this);
        user =new User();
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signId) {
            String uname=name.getText().toString();
            String upass=password.getText().toString();
            String uemail=email.getText().toString();
            int uphone=Integer.parseInt(phone.getText().toString());

            user.setName(uname);
            user.setPassword(upass);
            user.setEmail(uemail);
            user.setPhone(uphone);

           long insertUser= datbaseHelper.insertUser(user);
           if(insertUser>0){
               Toast.makeText(this,"User created successfully",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(SingUp.this, MainActivity.class);
               startActivity(intent);
           }
            else{
               Toast.makeText(this,"User created Failed",Toast.LENGTH_SHORT).show();
           }

        } else if (v.getId() == R.id.signBack) {
            Intent intent = new Intent(SingUp.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
