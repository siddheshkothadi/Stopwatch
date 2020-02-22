package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int seconds=0;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        else {
            seconds = 0;
            running = false;
        }
    }

    public void onStart(View view){
        running = true;
    }

    public void onPause(View view){
        running = false;
    }

    public void onReset(View view){
        running = false;
        seconds = 0;
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }

    @Override
    public void onStop(){
        super.onStop();
        running = false;
    }

    @Override
    public void onStart(){
        super.onStart();
        running = true;
    }

    public void runTimer(){
        final TextView timer = findViewById(R.id.timerView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =  seconds/3600;
                int minutes = (seconds%3600)/60;
                int second = seconds%60;
                String time_format = String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,second);
                timer.setText(time_format);
                if (running){
                    seconds++;
                }handler.postDelayed(this,1000);
            }
        });
    }
}