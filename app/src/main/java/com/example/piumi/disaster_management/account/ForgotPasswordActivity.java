package com.example.piumi.disaster_management.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.piumi.disaster_management.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    private Button resetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetPassword = findViewById (R.id.resetpwd_btn);
        resetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick (View view) {
        switch (view.getId()){
            case R.id.resetpwd_btn:
                resetPwd();
                break;

        }
    }

    //method to reset the password
    public void resetPwd(){

        //load the resetpassword window
        Intent intent = new Intent(ForgotPasswordActivity.this,ResetPwdActivity.class);
        startActivity(intent);
    }
}
