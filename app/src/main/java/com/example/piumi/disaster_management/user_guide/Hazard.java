package com.example.piumi.disaster_management.user_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.piumi.disaster_management.R;

public class Hazard extends AppCompatActivity {


    ListView disaster_list;
    String[] disastername = {"Tsunami","LandSlide","Flood","Earthquake","Cyclones","Drought","Urban Flood"};
    Integer[] img_id = {R.drawable.tsunami, R.drawable.landslide,R.drawable.sink , R.drawable.earthquake,R.drawable.umbrella,R.drawable.drought,R.drawable.flood};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        disaster_list = (ListView) findViewById(R.id.list_disaster);   //get the Listview layout in xml file
        CustomListView customListView = new CustomListView(this,disastername,img_id);   // send names and images to the custom view class and create a object of it
        disaster_list.setAdapter(customListView);
        disaster_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Hazard.this,Single_DisasterActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

}
