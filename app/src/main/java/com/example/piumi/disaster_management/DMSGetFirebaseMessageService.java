package com.example.piumi.disaster_management;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Piumi on 2/18/2018.
 */

public class DMSGetFirebaseMessageService extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Intent intent = new Intent(this,QA.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this);
        noBuilder.setContentTitle("Disaster Alerts");
        noBuilder.setContentText(remoteMessage.getNotification().getBody());
        noBuilder.setAutoCancel(true);
        noBuilder.setSmallIcon(R.mipmap.ic_launcher);
        noBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noBuilder.build());
    }
}
