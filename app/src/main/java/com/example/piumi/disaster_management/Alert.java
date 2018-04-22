package com.example.piumi.disaster_management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Alert extends AppCompatActivity {


    ListView alert_list;
    Integer[] img_id = {R.drawable.siren};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("notifications");
        Query query =  ref.orderByChild("visibility").equalTo("true").limitToLast ( 20 );
        query.addListenerForSingleValueEvent(

                new ValueEventListener () {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot

                        Map<String,Object> alertlist = (Map<String, Object>) dataSnapshot.getValue();
                        showAlerts (alertlist );

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });



    }

    private void showAlerts(Map<String,Object> alerts) {

        ArrayList<String> alert_body = new ArrayList <String> ( );
        String title = "Disaster Alerts";

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : alerts.entrySet()){

            Map singleLocation = (Map) entry.getValue();
            String single_alert = (String)singleLocation.get ("body");
            alert_body.add (single_alert);



        }
        alert_list = (ListView) findViewById(R.id.list_alerts);   //get the Listview layout in xml file
        AlertList alertList = new AlertList ( this,alert_body,img_id,title );// send names and images to the custom view class and create a object of it
        alert_list.setAdapter( alertList );
        /*helpcenters_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
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
        );*/


    }


}
