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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText txtEmail;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    private Button btnRegister;
    private EditText firstName;
    private EditText lastName;
    private EditText confirmPwd;
    private static  String TAG;
    private Button btn_cancel;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //create firebase instance
        firebaseAuth = FirebaseAuth.getInstance();
        //create firebase database instance
        database = FirebaseDatabase.getInstance();
        //get the mobile_user table  reference
        databaseReference = database.getReference("mobile_user");

        TAG = RegisterActivity.class.getName();

        //achieving the elements in the register window
        txtEmail = (EditText)findViewById(R.id.reg_email);
        txtPwd = (EditText)findViewById(R.id.reg_password);
        firstName = (EditText)findViewById(R.id.firstname);
        lastName = (EditText)findViewById(R.id.lastname);
        confirmPwd = (EditText)findViewById(R.id.confirm_password);
        btnRegister = findViewById(R.id.register_btn);
        btn_cancel = findViewById(R.id.cancel_btn);

        //setting onclick listners
        btnRegister.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.register_btn:
                registerUser();
                break;
            case R.id.cancel_btn:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
        }

    }

    @Override
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registerUser(){
        final String email = txtEmail.getText().toString().trim();
        String password = txtPwd.getText().toString().trim();
        String confirm_password = confirmPwd.getText().toString().trim();
        final String firstname = firstName.getText().toString().trim();
        final String lastname = lastName.getText().toString().trim();

        if(TextUtils.isEmpty(firstname)){
            Toast.makeText(this,"Please enter first name",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(lastname)){
            Toast.makeText(this,"Please enter last name",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }else{
            if(password.length()<=7) {
                Toast.makeText(this,"Please enter password with more than 7 characters",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if(TextUtils.isEmpty(confirm_password)){
            Toast.makeText(this,"Please enter password again",Toast.LENGTH_SHORT).show();
            return;
        }





        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // call the function to verify the given email address
                            verifyEmail();

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String user_id = user.getUid();
                            databaseReference = database.getReference("mobile_user/"+user_id);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("first_name",firstname);
                            map.put("last_name",lastname);
                            map.put("email",email);

                            //add login detail to the database
                            databaseReference.setValue(map);

                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void verifyEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Check your Email for verification",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });
        }
    }
}
