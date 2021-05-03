package com.example.doggotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity
{
    TextView timeTextView;
    SeekBar timeSeekBar;
    Button timerButton;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    ImageView doggoInitial, doggoWait, doggoFinal;

    boolean counterActive = false;

    public void resetTimer()
    {
        timeTextView.setText("0.30");
        timeSeekBar.setProgress(30);
        timeSeekBar.setEnabled(true);
        countDownTimer.cancel();
        timerButton.setText("STMART");
        counterActive = false;
        mediaPlayer.stop();
    }
    public void onStartClick(View view)
    {
        if(counterActive)
        {
            doggoInitial.animate().alpha(1);
            doggoWait.animate().alpha(0);
            doggoFinal.animate().alpha(0);
            resetTimer();
        }
        else
         {
            counterActive = true;
            timeSeekBar.setEnabled(false);
            doggoInitial.animate().alpha(0);
            doggoWait.animate().alpha(1);
            timerButton.setText("STOMP");

            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000, 1000) {
                public void onTick(long l)
                {
                    updateTime((int) l / 1000);
                }

                public void onFinish()
                {
                    doggoWait.animate().alpha(0);
                    doggoFinal.animate().alpha(1);
                    mediaPlayer.start();
                }
            }.start();
        }
    }

    public void updateTime(int secondsLeft)
    {
        int minute = secondsLeft/60;
        int seconds = secondsLeft - (minute*60);

        String secondString = Integer.toString(seconds);
        if(secondString.equals("0"))
        {
            secondString = "00";
        }
        if(secondString.equals("1"))
        {
            secondString = "01";
        }
        if(secondString.equals("2"))
        {
            secondString = "02";
        }
        if(secondString.equals("3"))
        {
            secondString = "03";
        }
        if(secondString.equals("4"))
        {
            secondString = "04";
        }
        if(secondString.equals("5"))
        {
            secondString = "05";
        }
        if(secondString.equals("6"))
        {
            secondString = "06";
        }
        if(secondString.equals("7"))
        {
            secondString = "07";
        }
        if(secondString.equals("8"))
        {
            secondString = "08";
        }
        if(secondString.equals("9"))
        {
            secondString = "09";
        }
        timeTextView.setText(Integer.toString(minute)+":"+secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = (SeekBar)findViewById(R.id.timeSeekBar);
        timeTextView = (TextView)findViewById(R.id.timerTextView);
        timerButton = (Button)findViewById(R.id.timerButton);
        doggoInitial = (ImageView)findViewById(R.id.doggoInitial);
        doggoWait = (ImageView)findViewById(R.id.doggoWait);
        doggoFinal = (ImageView)findViewById(R.id.doggoFinal);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.doogobark);

        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });


    }
}