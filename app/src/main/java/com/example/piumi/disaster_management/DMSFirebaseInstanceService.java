package com.example.piumi.disaster_management;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Piumi on 2/18/2018.
 */

public class DMSFirebaseInstanceService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN_PIYUddddd";

    @Override
    public  void onTokenRefresh(){
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);
        Log.d(REG_TOKEN,"piyumiiiiiiiiiiiiiiiiii");
        sendRegistrationToServer(recent_token);
    }

    private void sendRegistrationToServer(String recent_token) {
        System.out.println("i am hereeeeeeeeeeeeeeeeeeeeee"+recent_token);
    }

}


