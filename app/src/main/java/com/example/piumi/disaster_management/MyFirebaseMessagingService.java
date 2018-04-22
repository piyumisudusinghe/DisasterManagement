package com.example.piumi.disaster_management;

/**
 * Created by Piumi on 3/29/2018.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder (this);
        notiBuilder.setContentTitle (remoteMessage.getNotification ().getTitle ());
        notiBuilder.setContentText ( remoteMessage.getNotification ().getBody ());
        notiBuilder.setAutoCancel ( true );
        notiBuilder.setSmallIcon ( Integer.parseInt ( remoteMessage.getNotification().getIcon () ) );
        NotificationManager notificationManager = ( NotificationManager ) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify ( 0,notiBuilder.build () );

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void scheduleJob () {
    }
    private void handleNow() {
    }
}
