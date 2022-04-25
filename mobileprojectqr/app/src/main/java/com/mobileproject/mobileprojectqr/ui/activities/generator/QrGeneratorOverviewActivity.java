package com.mobileproject.mobileprojectqr.ui.activities.generator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileproject.mobileprojectqr.R;


public class QrGeneratorOverviewActivity extends AppCompatActivity {
    ListView listView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_generator);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        Button but1 = (Button) findViewById(R.id.button1);
        Button but2 = (Button) findViewById(R.id.button2);
        Button but3 = (Button) findViewById(R.id.button3);
        Button but4 = (Button) findViewById(R.id.button4);

        but1.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent int1 = new  Intent(QrGeneratorOverviewActivity.this, TextEnterActivity.class);
               startActivity(int1);
            }
        });

        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent int2 = new Intent(QrGeneratorOverviewActivity.this, MailEnterActivity.class);
                startActivity(int2);
            }
        });

        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent int3 = new Intent(QrGeneratorOverviewActivity.this,UrlEnterActivity.class);
                startActivity(int3);
            }
        });

        but4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent int5 = new Intent(QrGeneratorOverviewActivity.this,SmsEnterActivity.class);
                startActivity(int5);
            }
        });


    }



}

