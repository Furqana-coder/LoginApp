package com.example.loginapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import com.example.loginapp.Model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName,memail,mmobile;
    private Button mRegisterBtn;
    private String Name,Email,mobile;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_MOBILE = "number";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = (EditText)findViewById(R.id.editName);
        memail = (EditText)findViewById(R.id.editEmail);
        mmobile = (EditText)findViewById(R.id.editMobile);
        mRegisterBtn = (Button)findViewById(R.id.Register);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    String MobilePattern = "[0-9]{10}";
                    if (!mmobile.getText().toString().matches(MobilePattern)) {

                        Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();

                    } else {

                        SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor mEditor = mSharedPreference.edit();
                        mEditor.putString(PREF_NAME, Name);
                        mEditor.putString(PREF_EMAIL, Email);
                        mEditor.putString(PREF_MOBILE, mobile);
                        mEditor.apply();
                        finish();
                    }
                }
            }
        });
    }

    public boolean validate() {

        String MobilePattern = "[0-9]{10}";
        //String email1 = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]+";

        if (mName.length() > 25) {

            Toast.makeText(getApplicationContext(), "pls enter less the 25 character in user name", Toast.LENGTH_SHORT).show();
            return false;

        } else if (mName.length() == 0 || mmobile.length() == 0 || memail.length() ==
                0 ) {

            Toast.makeText(getApplicationContext(), "pls fill the empty fields", Toast.LENGTH_SHORT).show();
            return false;

        }else if (memail.getText().toString().matches(emailPattern)) {

            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            return true;

        } else if(!memail.getText().toString().matches(emailPattern)) {

            Toast.makeText(getApplicationContext(),"Please Enter Valid Email Address",Toast.LENGTH_SHORT).show();
            return false;

        }
                /*else
                }*/

        return false;


    }

}