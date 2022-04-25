package com.mobileproject.mobileprojectqr.ui.activities;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileproject.mobileprojectqr.R;

public class SplashActivity extends AppCompatActivity {

    private static final int code=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splashactivity);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent;
                mainIntent = new Intent(SplashActivity.this, ScannerActivity.class);

                startActivity(mainIntent);
                finish();
            }
        },code);


    }
}
