package com.yaho.facelapse;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerTime extends AsyncTask<String, Void, Long> {

    public static final String TIME_SERVER = "time.google.com";

    private Exception exception;

    protected Long doInBackground(String... urls) {
        NTPUDPClient timeClient = new NTPUDPClient();
        timeClient.setDefaultTimeout(3000);
        InetAddress inetAddress = null;
        TimeInfo timeInfo = null;

        try {
            inetAddress = InetAddress.getByName(TIME_SERVER);
            timeInfo = timeClient.getTime(inetAddress);
            long localTime = timeInfo.getReturnTime();
            long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.e("UnknownHostException: ", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException: ", e.getMessage());
        }
        return timeInfo.getMessage().getTransmitTimeStamp().getTime();
    }
}

