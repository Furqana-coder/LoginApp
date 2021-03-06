package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    public static String str_login_test;

    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        // here initializing the shared preference
        sh = getSharedPreferences("PREFERENCE", 0);
        editor = sh.edit();

        // check here if user is login or not
        str_login_test = sh.getString("PREFERENCE", null);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                /*
                 * if user login test is true on oncreate then redirect the user
                 * to result page
                 */

                if (str_login_test != null
                        && !str_login_test.toString().trim().equals("")) {
                    Intent send = new Intent(getApplicationContext(),
                            Main3Activity.class);
                    startActivity(send);
                }
                /*
                 * if user login test is false on oncreate then redirect the
                 * user to login & registration page
                 */
                else {

                    Intent send = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(send);

                }
            }

        }, 1000);

    }

}
