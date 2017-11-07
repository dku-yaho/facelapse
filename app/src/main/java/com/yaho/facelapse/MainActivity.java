package com.yaho.facelapse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


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
                break;
        }
    }

}
