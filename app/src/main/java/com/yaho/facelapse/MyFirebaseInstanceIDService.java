package com.yaho.facelapse;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by qoren on 2017-12-05.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";

    @Override
    public void onTokenRefresh() {
        // to get instance ID token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);   //sending the token to server
    }

    // [END refresh_token]

    private void sendRegistrationToServer(String token) {
    }
}
