package com.example.piumi.disaster_management.account;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piumi.disaster_management.MainActivity;
import com.example.piumi.disaster_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{

    private Button btnCreateAccount;
    private Button btnForgetPwd;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private static String TAG;
    private EditText txtEmail;
    private EditText txtPwd;

    public static String getTAG () {
        return TAG;
    }

    public static void setTAG ( String TAG ) {
        LoginActivity.TAG = TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating the firebase instance
        setFirebaseAuth (FirebaseAuth.getInstance());

        //achieve elements in the login window


        btnCreateAccount =  findViewById(R.id.btn_createAccount);
        btnForgetPwd=findViewById(R.id.btn_forgotPwd);
        btnLogin = findViewById(R.id.btn_login);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPwd = (EditText)findViewById(R.id.txt_pwd);

        //adding listners to the buttons
        getBtnCreateAccount ().setOnClickListener(this);
        getBtnForgetPwd ().setOnClickListener(this);
        getBtnLogin ().setOnClickListener(this);

        setTAG (LoginActivity.class.getName());

    }



    @Override
    public void onClick(View view) {

        //load the  relevant windows based on the click events in the login window
        switch (view.getId()){
            case R.id.btn_createAccount:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_forgotPwd:
                Intent intent2 = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_login:
                signUp();
        }

    }

    public void signUp(){
        final String email = getTxtEmail ().getText().toString().trim();
        String password = getTxtPwd ().getText().toString().trim();

        //check given fields are empty or not
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;

        }

        //validate the password
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }



        //get firebase instance and check the validity of given user credentilas
        getFirebaseAuth ().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(getTAG (), "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Login is successfull.",
                                    Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = firebaseAuth.getCurrentUser();

                             updateUser(email);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(getTAG (), "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }


    public void updateUser(String email){

        Intent intent = new Intent (LoginActivity.this,MainActivity.class);
        startActivity(intent);
        //TextView txt = findViewById (R.id.usercircle);
        //String firstletter = email.substring(0, 1).toUpperCase ();
        //txt.setText (firstletter);


    }


    public Button getBtnCreateAccount () {
        return btnCreateAccount;
    }

    public void setBtnCreateAccount () {
        this.btnCreateAccount = btnCreateAccount;
    }

    public Button getBtnForgetPwd () {
        return btnForgetPwd;
    }

    public void setBtnForgetPwd ( Button btnForgetPwd ) {
        this.btnForgetPwd = btnForgetPwd;
    }

    public Button getBtnLogin () {
        return btnLogin;
    }

    public void setBtnLogin ( Button btnLogin ) {
        this.btnLogin = btnLogin;
    }

    public FirebaseAuth getFirebaseAuth () {
        return firebaseAuth;
    }

    public void setFirebaseAuth ( FirebaseAuth firebaseAuth ) {
        this.firebaseAuth = firebaseAuth;
    }

    public EditText getTxtEmail () {
        return txtEmail;
    }

    public void setTxtEmail ( EditText txtEmail ) {
        this.txtEmail = txtEmail;
    }

    public EditText getTxtPwd () {
        return txtPwd;
    }

    public void setTxtPwd ( EditText txtPwd ) {
        this.txtPwd = txtPwd;
    }
}
