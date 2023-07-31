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

public class MainActivity extends AppCompatActivity {
    TextView scoreText,timeText;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Button startScreenbtn;
    int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        startScreenbtn = findViewById(R.id.button2);


        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();

        score = 0;

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: "+l/1000);
                startScreenbtn.setEnabled(false);

            }

            @Override
            public void onFinish() {
                startScreenbtn.setEnabled(true);

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for (int i=0;i < imageArray.length;i++) {
                    imageArray[i].setVisibility(View.INVISIBLE);
                }
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Restart");
                    alert.setMessage("Your score: "+score+"\n Try Again?");

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
                            Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();

                        }
                    });
                alert.show();

            }
        }.start();

    }

    public void menu(View view) {

        Intent intent = new Intent(MainActivity.this, StartScreenActivity.class);
        startActivity(intent);

    }

    public void IncreaseScore(View view) {

        score++;
        scoreText.setText("Score: "+score);

    }

    public void hideImages() {

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
                handler.postDelayed(runnable,400);

            }
        };
        handler.post(runnable);
    }
}