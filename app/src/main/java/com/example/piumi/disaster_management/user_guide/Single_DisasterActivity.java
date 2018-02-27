package com.example.piumi.disaster_management.user_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.piumi.disaster_management.R;

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
        single_disaster.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Single_DisasterActivity.this,DroughtActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
