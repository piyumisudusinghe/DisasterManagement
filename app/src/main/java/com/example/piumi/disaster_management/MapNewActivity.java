package com.example.piumi.disaster_management;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapNewActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_new);
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapnew);
        mapFrag.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        LatLng pp = new LatLng(11.5448729,104.8921668);
        googleMap.addMarker(new MarkerOptions().position(pp).title("Phnom Penh"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pp));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
