package com.yaho.facelapse;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Pop up window
 *
 * set size
 * set external font
 */

public class Pop extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.manualwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        TextView tv_m = (TextView) findViewById(R.id.manualTitle);
        Typeface font_m = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv_m.setTypeface(font_m);

        TextView tv_m1 = (TextView) findViewById(R.id.manual1);
        Typeface font_m1 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv_m1.setTypeface(font_m1);

        TextView tv_m2 = (TextView) findViewById(R.id.manual2);
        Typeface font_m2 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv_m2.setTypeface(font_m2);

        TextView tv_m3 = (TextView) findViewById(R.id.manual3);
        Typeface font_m3 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv_m3.setTypeface(font_m3);


    }

}
