package com.example.piumi.disaster_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{

    private Button btnCreateAccount;
    private Button btnForgetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btnCreateAccount =  findViewById(R.id.btn_createAccount);
        btnForgetPwd=findViewById(R.id.btn_forgotPwd);
        btnCreateAccount.setOnClickListener(this);
        btnForgetPwd.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_createAccount:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_forgotPwd:
                Intent intent2 = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent2);
                break;


        }

    }
}
