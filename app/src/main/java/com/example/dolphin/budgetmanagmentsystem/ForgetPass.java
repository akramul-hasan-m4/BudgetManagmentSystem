package com.example.dolphin.budgetmanagmentsystem;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dolphin.budgetmanagmentsystem.model.User;

import java.util.List;

public class ForgetPass extends AppCompatActivity implements View.OnClickListener {
    EditText serchEmail, searchphone;
    Button search, ans;
    TextView setname, setpass;
    DatbaseHelper datbaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        serchEmail = findViewById(R.id.searchEmail);
        searchphone = findViewById(R.id.searchphone);
        search = findViewById(R.id.btnSearch);
        ans = findViewById(R.id.btnAns);
        setname = findViewById(R.id.viewname);
        setpass = findViewById(R.id.viewpass);

        datbaseHelper = new DatbaseHelper(this);

        search.setOnClickListener(this);
        ans.setOnClickListener(this);

        searchphone.setVisibility(View.INVISIBLE);
        ans.setVisibility(View.INVISIBLE);
        setname.setVisibility(View.INVISIBLE);
        setpass.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSearch) {
            String sm = serchEmail.getText().toString();
            boolean bool = datbaseHelper.search(sm);
            if (bool == true) {
                serchEmail.setVisibility(View.INVISIBLE);
                search.setVisibility(View.INVISIBLE);
                searchphone.setVisibility(View.VISIBLE);
                ans.setVisibility(View.VISIBLE);

            } else {
                Toast.makeText(this, "User Not found", Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId() == R.id.btnAns) {
            int phon = Integer.parseInt(searchphone.getText().toString());
            try{
            List<User> list = datbaseHelper.searchbyPhone(phon);
            Log.d("list",list.toString());
            if (list.size() > 0) {
                setname.setVisibility(View.VISIBLE);
                setpass.setVisibility(View.VISIBLE);
                setname.setText("User = "+list.get(0).getName().toString());
                setpass.setText("Password = "+list.get(0).getPassword().toString());
            } else {
            }}catch (Exception e){
                Log.d("msg2","======"+e.getMessage());
            }
        }
    }
}
