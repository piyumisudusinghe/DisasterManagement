package com.example.piumi.disaster_management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Single_DisasterActivity extends AppCompatActivity {


ListView single_disaster;
String[] disasterlist = {"Introduction","Do's and Dont's","Emergency List","Build and Recovery","Guidelines"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single__disaster);
        single_disaster = (ListView) findViewById(R.id.single_disaster_listview);
        CustomSingleDisasterListView customSingleDisasterListView = new CustomSingleDisasterListView(this,disasterlist);//get the Listview layout in xml file
        single_disaster.setAdapter(customSingleDisasterListView);
    }
}
