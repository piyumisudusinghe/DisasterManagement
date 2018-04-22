package com.example.piumi.disaster_management.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.piumi.disaster_management.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap map;
    FirebaseDatabase firebaseDatabase;
    private LatLngBounds Sri_Lanka;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_map );

        //create the map fragment
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapnew);
        mapFrag.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("mapview");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener () {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        java.util.Map<String,Object> maplocation = ( java.util.Map<String, Object> ) dataSnapshot.getValue();

                        showLocations(maplocation);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void showLocations( java.util.Map<String,Object> locations) {
        Sri_Lanka = new LatLngBounds(
                new LatLng (5,79.9), new LatLng(10, 82));
        map.moveCamera( CameraUpdateFactory.newLatLngBounds(Sri_Lanka, 0));

        //iterate through each user, ignoring their UID
        for (java.util.Map.Entry<String, Object> entry : locations.entrySet()){

            java.util.Map singleLocation = ( java.util.Map ) entry.getValue();
            String name = entry.getKey().toLowerCase();
            String locationName = (String)singleLocation.get ("disaster");
            String mapTittle = name +" - " + locationName;
            Double lat = (Double)singleLocation.get("lat");
            Double lng = (Double) singleLocation.get("long");
            LatLng pp = new LatLng(lat,lng);

            //adding a marker to the location in the map
            map.addMarker(new MarkerOptions ().position(pp).title(mapTittle));

        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
