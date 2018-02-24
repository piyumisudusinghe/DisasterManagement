package com.example.piumi.disaster_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Map;

public class QA extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ListView user_list;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startQAForum();
    }




    public void startQAForum(){
        if(firebaseAuth.getInstance().getCurrentUser() != null){
            firebaseDatabase = FirebaseDatabase.getInstance();

            databaseReference = firebaseDatabase.getReference().child("admin_user");

            databaseReference.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map of users in datasnapshot
                            Map<String,Object> available_admins = (Map<String, Object>) dataSnapshot.getValue();

                            showusers(available_admins);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });

        }else{
            Toast.makeText(this, "You need to login before uae QA forum", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QA.this,LoginActivity.class);
            startActivity(intent);
        }

    }

    public void showusers(Map<String,Object> available_admins){

        ArrayList<String> useremail = new ArrayList<>();
        ArrayList<String> username = new ArrayList<>();
        for (Map.Entry<String, Object> entry : available_admins.entrySet())
        {
            Map singleUser = (Map) entry.getValue();
            String email = (String) singleUser.get("email");
            String first_name = (String) singleUser.get("first_name");
            String last_name = (String) singleUser.get("last_name");
           // String availability = (String)singleUser.get("availability") ;
            String fullname = (first_name + " " + last_name);
            /*if(availability == "true"){*/
                useremail.add(email);
                username.add(fullname);
            //}

        }

        user_list = (ListView)findViewById(R.id.list_user);   //get the Listview layout in xml file
        AdminUserList adminUserList = new AdminUserList(this,useremail,username);   // send names and images to the custom view class and create a object of it
        user_list.setAdapter(adminUserList);
        user_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(QA.this,ChatMine.class);
                        startActivity(intent);
                    }
                }
        );
    }






}
