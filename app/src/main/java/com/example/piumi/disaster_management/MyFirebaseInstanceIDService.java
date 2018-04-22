package com.example.piumi.disaster_management;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Piumi on 3/29/2018.
 */


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userid;
    public static String token;
    @Override
    public void onTokenRefresh () {

        //For registration of token
        String refreshedToken = FirebaseInstanceId.getInstance ( ).getToken ( );


        //To displaying token on logcat
        Log.d ( "TTTT:----------------", refreshedToken );
        sendRegistrationToServer(refreshedToken);
    }



    private void sendRegistrationToServer ( String refreshedToken ) {
        MyFirebaseInstanceIDService.token = refreshedToken;
        //store the registration token of the device in the firebase
        /*this.userid = firebaseAuth.getCurrentUser ( ) .getUid ();
        databaseReference = database.getReference ("fcmTokens_MobileUser/");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(userid,refreshedToken);
        databaseReference.setValue(map);*/




    }


}




