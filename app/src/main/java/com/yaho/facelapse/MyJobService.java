package com.yaho.facelapse;

import android.app.job.JobService;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;

/**
 * Created by qoren on 2017-12-05.
 */

public class MyJobService extends com.firebase.jobdispatcher.JobService {

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Performing long running task in scheduled job");
        // TODO(developer): add long running task here.
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
