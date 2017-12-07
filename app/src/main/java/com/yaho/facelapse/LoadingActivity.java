package com.yaho.facelapse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Loading 화면
 */

public class LoadingActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /* set font for loading screen text*/
        TextView tv = (TextView) findViewById(R.id.L_english);
        Typeface font = Typeface.createFromAsset(getAssets(), "AmaticSC-Regular.ttf");
        tv.setTypeface(font);
        TextView tv2 = (TextView) findViewById(R.id.L_korean);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "DXPnM.ttf");
        tv2.setTypeface(font2);
        TextView tv3 = (TextView) findViewById(R.id.L_FaceLapse);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "AmaticSC-Bold.ttf");
        tv3.setTypeface(font3);
        TextView tv4 = (TextView) findViewById(R.id.L_copyright);
        Typeface font4 = Typeface.createFromAsset(getAssets(), "AmaticSC-Regular.ttf");
        tv4.setTypeface(font4);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler() , 3000); // 3초 후에 hd Handler 실행

/*
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000); // 3초 후 로딩화면 닫기
*/
/*
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
*/
    }

    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
            LoadingActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }

}
