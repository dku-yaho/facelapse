package com.yaho.facelapse;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


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

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonselfie:
                // SELFIE 버튼이 눌렸을 때
                Toast.makeText(this, "SELFIE button clicked", Toast.LENGTH_LONG).show();
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

}
