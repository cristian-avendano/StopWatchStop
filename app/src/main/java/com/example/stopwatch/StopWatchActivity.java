package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Locale;

public class StopWatchActivity extends Activity {
    private int seconds = 0;
    private boolean running;
    private int vuelta = 0;
    String time ="";
    String aux = "";

    private String timeVueltaVal [] = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
    }

    public void onCLickStart(View view) {
        running = true;
    }

    public void onCLickStop(View view) {
        running = false;
    }

    public void onCLickReset(View view) {
        running = false;
        seconds = 0;
        vuelta = 0;
        timeVueltaVal = new String[5];
        time = "";
    }

    private void runTimer(){
        final TextView timeView= findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);

                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    public void onCLickVuelta(View view) {
        final TextView timeVuelta= findViewById(R.id.time_vuelta);

        if (running == false){
            running = true;
        }
        else{
            if (vuelta < 5){
                vuelta++;
                aux = aux + "vuelta:" + vuelta + ":"  + time + "\n";
                timeVuelta.setText(aux);
                seconds = 0;
                if(vuelta==5){
                    running = false;
                    seconds = 0;
                }
            }else{
                running = false;
                seconds = 0;
                vuelta = 0;
                time = "";
                aux = "";
                timeVuelta.setText("");
            }
        }
    }
}