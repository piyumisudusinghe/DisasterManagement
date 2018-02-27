package com.example.piumi.disaster_management.question_forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.piumi.disaster_management.R;
import com.example.piumi.disaster_management.account.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
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
        if(firebaseAuth.getCurrentUser() != null){
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
            Toast.makeText(this, "You need to login before use QA forum", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QA.this,LoginActivity.class);
            startActivity(intent);
        }

    }

    public void showusers(Map<String,Object> available_admins){

        final ArrayList<String> useremail = new ArrayList<>();
        ArrayList<String> username = new ArrayList<>();
        final ArrayList<String> adminId = new ArrayList<>();
        for (Map.Entry<String, Object> entry : available_admins.entrySet())
        {
            String admin_uid =entry.getKey();
            Map singleUser = (Map) entry.getValue();
            String email = (String) singleUser.get("email");
            String first_name = (String) singleUser.get("first_name");
            String last_name = (String) singleUser.get("last_name");
           // String availability = (String)singleUser.get("availability") ;
            String fullname = (first_name + " " + last_name);
            /*if(availability == "true"){*/
                useremail.add(email);
                username.add(fullname);
                adminId.add(admin_uid);
            //}

        }

        user_list = (ListView)findViewById(R.id.list_user);   //get the Listview layout in xml file
        AdminUserList adminUserList = new AdminUserList(this,useremail,username);   // send names and images to the custom view class and create a object of it
        user_list.setAdapter(adminUserList);
        user_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv1 = (TextView) view.findViewById(R.id.user_email);
                        String email = (String) tv1.getText();
                        int admin_index = useremail.indexOf(email);
                        String selected_admin = adminId.get(admin_index);
                        Intent intent = new Intent(QA.this, ChatMine.class);
                        intent.putExtra("adminId",selected_admin);
                        startActivity(intent);
                    }
                }
        );
    }






}
