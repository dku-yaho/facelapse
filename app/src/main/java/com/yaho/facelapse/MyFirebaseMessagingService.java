package com.yaho.facelapse;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qoren on 2017-12-05.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Calling method to generate notification
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH.mm");//데모버전 매분마다 사진찍기 가능
        String datest = dateFormat.format(date);
        String now = dateFormat.format(date);
        String messageBodyr = remoteMessage.getNotification().getBody();
        sendNotification(messageBodyr);
        while(true) {
            if(CheckTodayPhotoNum()==1)
                break;
            if (datest.equals(now)) {
                Date date1 = new Date(System.currentTimeMillis());
                now = dateFormat.format(date1);
            } else {
                sendNotification(messageBodyr);
                Date date1 = new Date(System.currentTimeMillis());
                datest = dateFormat.format(date1);
                now = dateFormat.format(date1);
            }
        }
    }

    public void showNotification(RemoteMessage remoteMessage) {

    }

    public void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);


        // String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle("Facelapse alert~")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    private int CheckTodayPhotoNum() {
        File sdCard = getFilesDir();
        File dirFile = new File(sdCard.getAbsolutePath() + "/camtest");
        //디렉토리가 존재하지 않는 경우
        if (dirFile.exists() == false) {
            return 0;
        }
        //디렉토리가 존재하면서 파일 유무 확인
        File[] fileList = dirFile.listFiles();
        for (File tempFile : fileList) {
            if (tempFile.isFile()) {//파일이 있는 경우
                String tempPath = tempFile.getParent();
                String tempFileName = tempFile.getName();
                //System.out.println("FileName="+tempFileName);
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat =
                        //new SimpleDateFormat("yyyy-MM-dd HH.mm");//데모버전 매분마다 사진찍기 가능
                        new SimpleDateFormat("yyyy-MM-dd");
                String fileName = dateFormat.format(date) + ".jpg";

                if (tempFileName.equals(fileName)) {//오늘 파일이 이미 만들어 진 경우 1 return
                    return 1;
                }
            }
        }
        return 0;//오늘 파일이 만들어지지 않은 경우 0 return
    }
}
