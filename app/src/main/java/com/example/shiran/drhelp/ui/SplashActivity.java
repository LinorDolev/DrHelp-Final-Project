package com.example.shiran.drhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.shiran.drhelp.R;


public class SplashActivity extends AppCompatActivity {

    Animation fromTop;
    ImageView app_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        app_imageView = findViewById(R.id.splash_image);
        fromTop = AnimationUtils.loadAnimation(this, R.anim.from_top);
        app_imageView.setAnimation(fromTop);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
