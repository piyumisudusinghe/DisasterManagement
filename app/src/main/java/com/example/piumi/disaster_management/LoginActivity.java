package com.example.piumi.disaster_management;


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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{

    private Button btnCreateAccount;
    private Button btnForgetPwd;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private static String TAG;
    private EditText txtEmail;
    private EditText txtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating the firebase instance
        firebaseAuth =  FirebaseAuth.getInstance();

        //achieve elements in the login window
        btnCreateAccount =  findViewById(R.id.btn_createAccount);
        btnForgetPwd=findViewById(R.id.btn_forgotPwd);
        btnLogin = findViewById(R.id.btn_login);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPwd = (EditText)findViewById(R.id.txt_pwd);

        //adding listners to the buttons
        btnCreateAccount.setOnClickListener(this);
        btnForgetPwd.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        TAG = LoginActivity.class.getName();

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
        String email = txtEmail.getText().toString().trim();
        String password = txtPwd.getText().toString().trim();

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
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Login is successfull.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }




}
