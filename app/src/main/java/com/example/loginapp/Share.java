package com.example.loginapp;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Share extends Activity {

    private Button message;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        message = (Button) findViewById(R.id.message);
        editText1 = (EditText) findViewById(R.id.editText1);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.length() == 0) {

                    Toast.makeText(getApplicationContext(), "please enter the data to send", Toast.LENGTH_SHORT).show();
                    return;

                }
                sendMessage();
            }

        });

    }
    public void sendMessage() {

        String whatsAppMessage = editText1.getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
        sendIntent.setType("text/plain");

        // Do not forget to add this to open whatsApp App specifically
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);

    }


}
