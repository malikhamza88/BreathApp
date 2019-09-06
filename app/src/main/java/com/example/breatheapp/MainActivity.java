package com.example.breatheapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breatheapp.Utils.prefs;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView breathsTxt,timeTxt,sessionTxt,guideTxt;
    private Button startButton;
    private prefs prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView=findViewById(R.id.imageView);
        breathsTxt=findViewById(R.id.breatheTakentxt);
        timeTxt=findViewById(R.id.lastBreathTxt);
        sessionTxt=findViewById(R.id.todayMintTxt);
        guideTxt=findViewById(R.id.guideTxt);
        startButton=findViewById(R.id.startButton);
        prefrences=new prefs(this);

        startIntroAnimation();

        breathsTxt.setText(MessageFormat.format("{0} Breaths ", prefrences.getBreath()));
        timeTxt.setText(prefrences.getDate());
        sessionTxt.setText(MessageFormat.format("{0} min today ", prefrences.getSession()));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

    }

    private void startIntroAnimation(){

        ViewAnimator.animate(guideTxt).scale(0,1)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {

                    }
                }).duration(1500)
                .start();

    }

    private void startAnimation(){

        ViewAnimator.animate(mImageView)
                .alpha(0,1)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        guideTxt.setText("Inhale ... Exhale");
                        startButton.setText("Stop");
                    }
                })
                .decelerate()
                .duration(1000)
                .thenAnimate(mImageView)
                .scale(0.02f,1.5f,0.02f)
                .rotation(360)
                .repeatCount(5)
                .accelerate()
                .duration(5000)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        guideTxt.setText("Good Job");
                        mImageView.setScaleX(1.0f);
                        mImageView.setScaleY(1.0f);
                        startButton.setText("Start");

                        prefrences.setSession(prefrences.getSession()+1);
                        prefrences.setBreath(prefrences.getBreath()+1);
                        prefrences.setDate(System.currentTimeMillis());

                        new CountDownTimer(2000,1000){

                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();

                            }
                        }.start();
                    }
                })

                .start();

    }

}
