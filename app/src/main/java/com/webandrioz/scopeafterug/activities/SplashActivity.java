package com.webandrioz.scopeafterug.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.webandrioz.scopeafterug.R;
import com.webandrioz.scopeafterug.utils.SharedPreferenceProvider;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        ImageView imgv = (ImageView) findViewById(R.id.splashImage);
        imgv.setScaleType(ImageView.ScaleType.FIT_XY);
        Log.e("Splash", "onCreate: "+"splash" );
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if(new SharedPreferenceProvider().fatchDataLegislators(SplashActivity.this,"LOGIN").equals("1")) {
                    Intent i = new Intent(SplashActivity.this, DomainActivity.class);
                    startActivity(i);
                    finish();
                    Log.e("SplashActivity", "run: "+"mdmd");
                }else {
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                    Log.e("SplashActivity", "run: "+"mdmdELSE");
                }


            }
        }, SPLASH_TIME_OUT);

    }
}
