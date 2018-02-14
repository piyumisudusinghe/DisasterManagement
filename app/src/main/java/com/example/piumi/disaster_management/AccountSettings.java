package com.example.piumi.disaster_management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

public class AccountSettings extends AppCompatActivity {



    private ImageButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        login = (ImageButton) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityLoginOrRegister();

            }
        });

    }

    public  void openActivityLoginOrRegister(){

        Intent intent = new Intent(AccountSettings.this,LoginActivity.class);
        startActivity(intent);
    }
}
