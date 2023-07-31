package com.emreerkilic.catchthemario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void startEasy(View view) {

        Intent intent = new Intent(StartScreenActivity.this,Easy.class);
        startActivity(intent);
    }
    public void startMedium(View view) {

        Intent intent = new Intent(StartScreenActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void startHard(View view) {

        Intent intent = new Intent(StartScreenActivity.this,Hard.class);
        startActivity(intent);

    }
}