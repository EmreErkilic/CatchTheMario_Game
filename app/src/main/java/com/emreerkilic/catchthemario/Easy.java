package com.emreerkilic.catchthemario;

import static com.emreerkilic.catchthemario.R.id.imageView18;

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

public class Easy extends AppCompatActivity {
    TextView scoreTextEasy,timeTextEasy;
    ImageView imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Button startScreenbtn;
    int scoreEasy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        scoreTextEasy = findViewById(R.id.scoreTextEasy);
        timeTextEasy = findViewById(R.id.timeTextEasy);

        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);

        startScreenbtn = findViewById(R.id.button3);


        imageArray = new ImageView[] {imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18};

        hideImagesEasy();

        scoreEasy = 0;

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeTextEasy.setText("Time: "+l/1000);
                startScreenbtn.setEnabled(false);

            }

            @Override
            public void onFinish() {
                startScreenbtn.setEnabled(true);

                timeTextEasy.setText("Time off");
                handler.removeCallbacks(runnable);
                for (int i=0;i < imageArray.length;i++) {
                    imageArray[i].setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(Easy.this);
                alert.setTitle("Restart");
                alert.setMessage("Your score: "+scoreEasy+"\n Try Again?");

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
                        Toast.makeText(Easy.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }
    public void hideImagesEasy() {
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
                handler.postDelayed(runnable,600);

            }
        };
        handler.post(runnable);
    }

    public void menuEasy(View view) {
        Intent intent = new Intent(Easy.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void IncreaseScoreEasy(View view) {
        scoreEasy++;
        scoreTextEasy.setText("Score: "+scoreEasy);
    }
}