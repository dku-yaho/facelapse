package com.yaho.facelapse;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Validate {
    long servertime;
    long localtime;

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
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.format(current);
    }

}