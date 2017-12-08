package com.yaho.facelapse;

import  android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

public class GenvidActivity extends AppCompatActivity{

    ProgressBar progressbar;
    ProgressBar progressbar2;
    Video video;
    boolean test = false;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genvid);
        progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        progressbar2 = (ProgressBar)findViewById(R.id.progressBar3);

        progressbar.setMax(100);
        progressbar2.setMax(100);

        video = new Video(this);

        MyThread threadtest = new MyThread();
        threadtest.setDaemon(true);
        threadtest.start();

        video.genVid();
        test = true;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                progressbar.setProgress(video.getProgress());
                progressbar2.setProgress(video.getProgress());
            }
        }
    };

    class MyThread extends Thread{
        public void run(){
            while(test == false){
                try{
                    sleep(1000);

                }catch (Exception e){
                    e.printStackTrace();
                }
                myHandler.sendEmptyMessage(0);
            }
        }
    }
}
