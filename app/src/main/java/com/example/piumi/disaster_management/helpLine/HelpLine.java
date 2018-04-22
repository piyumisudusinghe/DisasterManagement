package com.example.piumi.disaster_management.helpLine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piumi.disaster_management.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class HelpLine extends AppCompatActivity {



    ListView helpcenters_list;


    Integer[] img_id = {R.drawable.help};

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_help_line );
       /* showHelpCenters (*//* helpcenter*//* );*/
       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("helpline");
        ref.addListenerForSingleValueEvent(

                new ValueEventListener () {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot

                        Map<String,Object> helpcenter = (Map<String, Object>) dataSnapshot.getValue();
                        showHelpCenters (helpcenter );

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });




    }
    private void showHelpCenters(Map<String,Object> location) {

        ArrayList<String>  location_name = new ArrayList <String> ( );
        ArrayList<String>  tel_nos = new ArrayList <String> ( );

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : location.entrySet()){

            Map singleLocation = (Map) entry.getValue();
            String tel_no = (String)singleLocation.get ("tel_no");
            String name = entry.getKey().toLowerCase();
            location_name.add (name);
            tel_nos.add ( tel_no );


        }
        helpcenters_list = (ListView) findViewById(R.id.list_helpline);   //get the Listview layout in xml file
        CallActivity callActivity = new CallActivity (this,location_name,img_id,tel_nos);   // send names and images to the custom view class and create a object of it
        helpcenters_list.setAdapter(callActivity);
        helpcenters_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv1 = (TextView) view.findViewById(R.id.helpcenter_location);
                        TextView tv2 = (TextView) view.findViewById(R.id.tel_no);
                        String helpcenter_place = (String) tv1.getText();
                        String tel_num = (String) tv2.getText();
                        Intent intent = new Intent(HelpLine.this,CallHelpLine.class);
                        intent.putExtra("place",helpcenter_place);
                        intent.putExtra ( "tel_no", tel_num);
                        startActivity(intent);
                    }
                }
        );


    }

}


