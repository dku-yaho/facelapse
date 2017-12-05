package com.yaho.facelapse;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Validate {
    Context mContext;
    long servertime = 0;
    long localtime = 0;
    private static final String TAG = "Validate";

    public Validate(Context mContext){
        this.mContext = mContext;
    }

    public void setServertime() throws ExecutionException, InterruptedException {
        this.servertime = new ServerTime().execute().get();
    }

    public void setLocaltime() {
        this.localtime = System.currentTimeMillis();
    }

    public String getServertime() {
        try {
            setServertime();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setLocaltime();
        Log.e("Servertime", Long.toString(servertime));
        return returnString(servertime);
    }

    public String getLocaltime() {
        setLocaltime();
        Log.e("Localtime", Long.toString(localtime));
        return returnString(localtime);
    }

    private String returnString(long time){
        Date current = new Date(time);
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        return transFormat.format(current);
    }

    public boolean validateTime(){
        if (!getLocaltime().equals(getServertime())){
            Log.e(TAG,"Time Validation Error");
            return false;
        } else {
            Log.e(TAG, "Time Validation Success");
            return true;
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connect = (ConnectivityManager)this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connect.getActiveNetworkInfo() != null;
    }
}
