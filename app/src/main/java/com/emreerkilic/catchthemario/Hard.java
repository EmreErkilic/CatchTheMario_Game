package com.emreerkilic.catchthemario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Hard extends AppCompatActivity {
    TextView scoreTextHard,timeTextHard;
    ImageView imageView19,imageView20,imageView21,imageView22,imageView23,imageView24,imageView25,imageView26,imageView27;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Button startScreenbtn;
    int scoreHard;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        scoreTextHard = findViewById(R.id.scoreTextHard);
        timeTextHard = findViewById(R.id.timeTextHard);

        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);
        imageView25 = findViewById(R.id.imageView25);
        imageView26 = findViewById(R.id.imageView26);
        imageView27 = findViewById(R.id.imageView27);

        startScreenbtn = findViewById(R.id.button5);


        imageArray = new ImageView[] {imageView19,imageView20,imageView21,imageView22,imageView23,imageView24,imageView25,imageView26,imageView27};

        hideImagesHard();

        scoreHard = 0;

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeTextHard.setText("Time: "+l/1000);
                startScreenbtn.setEnabled(false);
            }

            @Override
            public void onFinish() {
                startScreenbtn.setEnabled(true);

                timeTextHard.setText("Time off");
                handler.removeCallbacks(runnable);
                for (int i=0;i < imageArray.length;i++) {
                    imageArray[i].setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(Hard.this);
                alert.setTitle("Restart");
                alert.setMessage("Your score: "+scoreHard+"\n Try Again?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Hard.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void menuHard(View view) {
        Intent intent = new Intent(Hard.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void hideImagesHard() {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (int i=0;i < imageArray.length;i++) {
                    imageArray[i].setVisibility(View.INVISIBLE);
                }

                Random random =new Random();
                int a = random.nextInt(9);
                imageArray[a].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,200);

            }
        };
        handler.post(runnable);
    }

    public void IncreaseScoreHard(View view) {
        scoreHard++;
        scoreTextHard.setText("Score: "+scoreHard);
    }
}