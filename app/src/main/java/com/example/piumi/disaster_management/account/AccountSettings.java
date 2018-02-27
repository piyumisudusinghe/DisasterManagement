package com.example.piumi.disaster_management.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;

import com.example.piumi.disaster_management.R;

public class AccountSettings extends AppCompatActivity {

    private ImageButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        //setting a click listner to the button
        setLogin_btn ((ImageButton) findViewById(R.id.btnLogin));
        getLogin_btn ().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //call the function to proceed the login activity
                openActivityLoginOrRegister();

            }
        });

    }

    public  void openActivityLoginOrRegister(){

        //load the login window
        Intent intent = new Intent(AccountSettings.this,LoginActivity.class);
        startActivity(intent);
    }

    public ImageButton getLogin_btn () {
        return login_btn;
    }

    public void setLogin_btn ( ImageButton login_btn ) {
        this.login_btn = login_btn;
    }
}
