package com.example.piumi.disaster_management.question_forum;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.piumi.disaster_management.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class ChatMine extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String selected_admin;
    private ListView user_list;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_chat_mine);
        Bundle extras = getIntent ().getExtras ();

        if (extras != null) {
            selected_admin = extras.getString ("selected_admin");
        }
        //showEarlyMessages();
        getChildValues3 ();

    }


    public void showEarlyMessages () {
        //firebaseAuth = FirebaseAuth.getInstance ();
        //firebaseDatabase = FirebaseDatabase.getInstance ();
         if (firebaseAuth.getCurrentUser() != null){
             String hai ="k";
             //final String mobile_UID = firebaseAuth.getCurrentUser ().getUid ();
            // String ref = "question_forum/" + mobile_UID;
             //final String current_user_mail = firebaseAuth.getCurrentUser ().getEmail ();
             /*if (selected_admin != null) {
                 databaseReference = firebaseDatabase.getReference ().child ("question_forum/WaibQXarLpPBgr5RkWMXFTmwhQl2");
                 databaseReference.orderByKey().equalTo(selected_admin).addListenerForSingleValueEvent (new ValueEventListener () {
                     @Override
                     public void onDataChange (DataSnapshot dataSnapshot) {
                         if (dataSnapshot.exists ()) {
                             // dataSnapshot is the "issue" node with all children with id 0
                             Map<String, Object> sub_elements = (Map<String, Object>) dataSnapshot.getValue ();
                             // do something with the individual "issues"
                             getChildValues (sub_elements, "piyumi.15@cse.mrt.ac.lk");
                         }
                     }


                     @Override
                     public void onCancelled (DatabaseError databaseError) {

                     }
                 });




             }else{
                 Toast.makeText(this,"Please select a admin to ask a question",Toast.LENGTH_SHORT).show();

             }*/
         }

    }

    public void getChildValues (Map<String, Object> sub_elements, String usermail) {

        ArrayList<String> msg_time = new ArrayList<> ();
        ArrayList<String> msg = new ArrayList<> ();
        ArrayList<String> msg_sender = new ArrayList<> ();
        for (Map.Entry<String, Object> entry : sub_elements.entrySet ()) {
            Map singleObject = (Map) entry.getValue ();
            String time = (String) singleObject.get ("time");
            String message = (String) singleObject.get ("message");
            String sender = (String) singleObject.get ("sender");
            msg.add (message);
            msg_time.add (time);
            msg_sender.add (sender);

        }

        user_list = (ListView) findViewById (R.id.list_single_userchat);   //get the Listview layout in xml file
        MessageList messageList = new MessageList (this, msg, msg_time, msg_sender, usermail);   // send names and images to the custom view class and create a object of it
        user_list.setAdapter (messageList);


    }

    public void getChildValues3 () {

        ArrayList<String> msg_time = new ArrayList<> ();
        ArrayList<String> msg = new ArrayList<> ();
        ArrayList<String> msg_sender = new ArrayList<> ();
        msg_time.add ("2018/02/20 10:58:01");
        msg_time.add ("2018/02/20 10:20:01");
        msg_time.add ("2018/02/20 10:56:01");
        msg.add ("What is mean by urban flood");
        msg.add ("Urban flood means flood conditions occur near town areas");
        msg.add ("what type of disasters which I have face if I build house near uma oya?");
        msg_sender.add ("piyumi.15@cse.mrt.ac.lk");
        msg_sender.add ("supimi.15@cse.mrt.ac.lk");
        msg_sender.add ("piyumi.15@cse.mrt.ac.lk");
        user_list = (ListView) findViewById (R.id.list_single_userchat);   //get the Listview layout in xml file
        MessageList messageList = new MessageList (this, msg, msg_time, msg_sender, "piyumi.15@cse.mrt.ac.lk");   // send names and images to the custom view class and create a object of it
        user_list.setAdapter (messageList);


    }

}
