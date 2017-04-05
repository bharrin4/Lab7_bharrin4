package com.a40333.bharrin4.lab2_bharrin4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by User on 2/1/2017.
 */


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView lep = (ImageView) findViewById(R.id.lep);
        String mDrawableName = "lep";
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        lep.setImageResource(resID);


        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

}
