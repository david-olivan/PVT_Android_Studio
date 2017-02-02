package com.example.usuario.pvt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Usuario on 14/01/2017.
 */

public class PVT extends Activity {
    /*Variables I'm importing from another Activities*/
    private String EXTRA_USER;
    private String EXTRA_TEST;
    List<String> Survey_answers = new ArrayList<String>();

    /*Variables I'm using in this Activity*/
    private ImageView stimulus;
    private long onsetTime;
    List<String> aReactionTime = new ArrayList<String>();

    /*This method runs an intent to change this activity for the last one*/
    private void goToEnd () {
        Intent intent = new Intent (PVT.this,End.class);
        startActivity(intent);
    }

    /*This runnable should write all the data into a txt and after that execute the goToEnd()*/
    private class EndPVT implements Runnable{
        @Override
        public void run (){
            goToEnd();
        }
    }
    /*This runnable shows the desired image for a second, then hides it*/
    class MyLoop implements Runnable{
        @Override
        public void run(){
            new CountDownTimer(1000,1000){
              public void onTick(long millisUntilFinished){
                  onsetTime=System.currentTimeMillis();
                  stimulus.setEnabled(true);
              }
              public void onFinish(){stimulus.setEnabled(false);}
            }.start();
        }
    }

    /*UI gets initiated*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pvt_layout);

        /*Here are the variables I must import from previous activities.
        * There are two Strings and one string array.
        * I must write into a txt these three, and the array I'm creating in this activity*/
        Intent intent = getIntent();
        String participant_number = intent.getStringExtra(EXTRA_USER);
        String current_test = intent.getStringExtra(EXTRA_TEST);
        ArrayList<String> Survey_answers = intent.getStringArrayListExtra("Survey_answers");

        /*Here's what I'm going to use in this activity*/
        stimulus = (ImageView) findViewById(R.id.stimulus);
        stimulus.setEnabled(false);

        /*When a click is detected on an Enabled image,
        gets the reaction time, adds it to the aReactionTime array
        and Disables the image*/
        stimulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aReactionTime.add((Long.toString(System.currentTimeMillis()- onsetTime)));
                Log.d("My tag",Long.toString(System.currentTimeMillis()- onsetTime));
                stimulus.setEnabled(false);
            }
        });

        /*This should run the loop of blank(2-10sec)-image(1sec)-blank(2-10sec), but it doesn't*/
        final ScheduledExecutorService sExecutor = Executors.newSingleThreadScheduledExecutor();
        sExecutor.scheduleWithFixedDelay(new MyLoop(),(long) (2000 + ((int) (Math.random() * 8000))),(long) (2000 + ((int) (Math.random() * 8000))), TimeUnit.MILLISECONDS);

        /*This runs the finalisation of the activity*/
        final Handler mHandler= new Handler();
        mHandler.postDelayed(new EndPVT(),300000);
    }
}




