package com.yaho.facelapse;

import android.app.job.JobService;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by qoren on 2017-12-05.
 */

public class MyJobService extends com.firebase.jobdispatcher.JobService{

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob (JobParameters jobParameters){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.d(TAG, currentDateTimeString);
        Log.d(TAG, "Performing long running task in scheduled job");
        return false;
    }

    @Override
    public boolean onStopJob (JobParameters jobParameters){
        Log.d(TAG, "Job cancelled!");
        return false;
    }

}