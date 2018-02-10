package com.example.piumi.disaster_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Account extends AppCompatActivity {


    private Button btnCreateAccount;
    private Button btnForgetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnCreateAccount = (Button) findViewById(R.id.createAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRegister();
            }
        });
        btnForgetPwd=(Button) findViewById(R.id.register_btn);
        btnForgetPwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityForgetPwd();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void openActivityRegister(){
        Intent intent = new Intent(Account.this,Register.class);
        Account.this.startActivity(intent);
    }
    public void openActivityForgetPwd(){
        Intent intent = new Intent(Account.this,ForgetPwd.class);
        Account.this.startActivity(intent);
    }


}
