package com.example.loginapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.nsd.NsdManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Captain Code on 04/10/2017.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.Main3Activity;
import com.example.loginapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText uname, pwd;
    Button loginBtn,Reg;
    SharedPreferences pref;
    Intent intent;
    private static final int REQUEST_CODE = 1;
    private static final String TAG = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = (EditText)findViewById(R.id.editMobile);
        //  pwd = (EditText)findViewById(R.id.txtPwd);
        loginBtn = (Button)findViewById(R.id.login);
        Reg = (Button)findViewById(R.id.Reg);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(LoginActivity.this, Main3Activity.class);
       if(pref.contains("username")){
          startActivity(intent);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                // String password = pwd.getText().toString();
                String pattern="[0-9]{10}";
                if(username.length()==0) {
                    Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }
                else
                if(!username.matches(pattern)){
                    Toast.makeText(getApplicationContext(), "enter 10 digit number", Toast.LENGTH_SHORT).show();
                }
                else{SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",username);
                    //editor.putString("password",password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, Main3Activity.class);
                    startActivity(intent);
                    setupViewPager();
                    verifyPermissions();
                    finish();
                }
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }});
    }
    private void verifyPermissions(){
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,Manifest.permission.READ_SMS,Manifest.permission.READ_CALL_LOG,Manifest.permission.ACCESS_FINE_LOCATION};

        if ((ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[2]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[3]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[4]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[5]) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[6]) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(LoginActivity.this,
                    permissions,
                    REQUEST_CODE);
        } else {
            setupViewPager();
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPermissions();
    }*/

    private void setupViewPager() {
    }
}




























/*
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final Boolean isloggedin=sharedPreferences.getBoolean("ISLOGGEDIN",false);
       /* if(isloggedin)
        {
            Intent main = new Intent(Login.this, LogoutActivity.class);
            startActivity(main);
        }final String required_email=sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
        final String required_password=sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");
        final EditText email_field=(EditText)findViewById(R.id.login_email);
        final EditText password_field=(EditText)findViewById(R.id.login_password);
        Button login=(Button)findViewById(R.id.login_button);
        Button register=(Button)findViewById(R.id.register_button);
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                         String email = email_field.getText().toString();
                                         String password = password_field.getText().toString();

                                             if (email.equals(required_email) && password.equals(required_password)) {
                                                 sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).commit();
                                                 Intent main = new Intent(Login.this, LogoutActivity.class);
                                                 startActivity(main);
                                             } else {
                                                 Toast.makeText(Login.this, "Email address or password is incorrect", Toast.LENGTH_LONG).show();
                                             }
                                         }


                                     private boolean validUserData() {

                                         if (email_field.length() == 0 || password_field.length() == 0)

                                             Toast.makeText(getApplicationContext(), "pls fill the empty fields", Toast.LENGTH_SHORT).show();
                                             return false;
                                     }


                                 });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register=new Intent(Login.this,Registration.class);
                startActivity(register);
                finish();
            }
        });



    }
}
*/