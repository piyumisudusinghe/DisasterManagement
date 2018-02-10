package com.example.piumi.disaster_management;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_account) {
          //Intent account = new Intent(MainActivity.this,Account.class);
          // startActivity(account);
           //Intent login = new Intent(MainActivity.this,Main2Activity.class);
           //startActivity(login);
           //Intent map = new Intent(MainActivity.this,Map.class);
           //startActivity(map);
           Intent intent = new Intent(MainActivity.this,AccountSettings.class);
           startActivity(intent);



        } else if (id == R.id.nav_map_view) {
           Intent map = new Intent(MainActivity.this,Map.class);
           startActivity(map);

          

        } else if (id == R.id.nav_alert_list) {
           Intent alert = new Intent(MainActivity.this,Alert.class);
           startActivity(alert);


        } else if (id == R.id.nav_hazards) {
           Intent hazard = new Intent(MainActivity.this,Hazard.class);
           startActivity(hazard);


        } else if (id == R.id.nav_question_forum) {
           Intent qa = new Intent(MainActivity.this,QA.class);
           startActivity(qa);


        } else if (id == R.id.nav_help_line) {
           Intent help = new Intent(MainActivity.this,Help.class);
           startActivity(help);


        }else if (id == R.id.nav_about_us) {
           Intent aboutus = new Intent(MainActivity.this,Aboutus.class);
           startActivity(aboutus);


        }else if (id == R.id.nav_feedback) {
           Intent feedback = new Intent(MainActivity.this, Feedback.class);
           startActivity(feedback);
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
