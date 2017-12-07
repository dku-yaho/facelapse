package com.yaho.facelapse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Validate validate;

    private static final String JOB_TAG = "MyJobService";
    FirebaseJobDispatcher mDispatcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        /* set font for title text*/
        TextView tv = (TextView) findViewById(R.id.FaceLapse);
        Typeface font = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv.setTypeface(font);

        /* set font for title text*/
        TextView tv2 = (TextView) findViewById(R.id.textSelfie);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv2.setTypeface(font2);

        /* set font for title text*/
        TextView tv3 = (TextView) findViewById(R.id.textAlbum);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv3.setTypeface(font3);

        validate = new Validate();
        if(validate.validateTime() == false){
            Toast.makeText(this, "Time Invalid", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    MainActivity.this.finish();
                }
            }, 100);
        }

        scheduleJob(this);

    }

    public void onClick(View view) throws ExecutionException, InterruptedException {
        switch (view.getId()){
            case R.id.buttonselfie:
                // SELFIE 버튼이 눌렸을 때
                Intent selfieintent = new Intent(getApplicationContext(), SelfieActivity.class);
                startActivity(selfieintent);
                break;
            case R.id.buttonalbum:
                // ALBUM 버튼이 눌렸을 때
                Toast.makeText(this, "ALBUM button clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonmanual:
                startActivity(new Intent(MainActivity.this,Pop.class));
                break;
        }
    }


    private void scheduleJob(Context context) {
        //creating new firebase job dispatcher
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        //creating new job and adding it with dispatcher
        Job myJob = mDispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(30, 60))
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
        mDispatcher.mustSchedule(myJob);
        //Toast.makeText(this, R.string.job_scheduled, Toast.LENGTH_LONG).show();
    }

    private void cancelJob(String jobTag) {
        if ("".equals(jobTag)) {
            mDispatcher.cancelAll();
        } else {
            mDispatcher.cancel(jobTag);
        }
       // Toast.makeText(this, R.string.job_cancelled, Toast.LENGTH_LONG).show();
    }


}