package com.example.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {
RadioButton r2,r1;
    static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NextActivity.this, StartActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(NextActivity.this, "" + r1.getText(), 2000).show();
              //  Toast.makeText(NextActivity.this, "" + r2.getText(), 2000).show();
            }

        });
        r2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NextActivity.this, StartActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(NextActivity.this, "" + r2.getText(), 2000).show();
            }
        });

    }
}
