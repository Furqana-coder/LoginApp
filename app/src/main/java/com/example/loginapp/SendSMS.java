package com.example.loginapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.loginapp.util.SMS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SendSMS extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    Button mselect,send;
    EditText mItemSelected;
    EditText edit;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    // ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        mselect = (Button) findViewById(R.id.btnselect);
        send = (Button) findViewById(R.id.send);
        mItemSelected = (EditText) findViewById(R.id.tvItemSelected);
        edit = (EditText) findViewById(R.id.edit);

        listItems = getResources().getStringArray(R.array.List);
        checkedItems = new boolean[listItems.length];
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sms = edit.getText().toString();
                String phoneNum = mItemSelected.getText().toString();
                if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                    if (checkPermission()) {

                            new SMS().sendSms(""+sms,phoneNum.toString());
                            Toast.makeText(SendSMS.this, "send " +phoneNum, Toast.LENGTH_SHORT).show();

                    }
                }else
                        Toast.makeText(SendSMS.this, "enter data", Toast.LENGTH_SHORT).show();
                }


        });

        mselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SendSMS.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if (isChecked) {
                            mUserItems.add(position);
                        } else {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        mItemSelected.setText(item);
                        // read
                        if (mItemSelected.getText().toString().length() > 0) {

                        }

                        else {
                            Toast.makeText(SendSMS.this, "Please Select number!", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
    }


    public void GetContactsIntoDb() {

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            // name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            //StoreContacts.add(name + " " + ":" + " " + phonenumber);
            //StoreContacts.add(phonenumber);
            //myDb.insertData(name,phonenumber);
            //  myDb.insertData(name, phonenumber);
            // Log.d("list", myDb.toString());

        }
        if (cursor != null) {
            cursor.close();
        }
    }


    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(SendSMS.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }

    }

    public class SMS {
        public void sendSms(final String sms, final String number) {
            new AsyncTask<Void,Void,String>(){


                @Override
                protected String doInBackground(Void... params) {
                    try {
                        // Construct data
                        System.out.println("sms"+sms);
                        System.out.println("number"+number);
                        String apiKey = "apikey=" + "5acGWHAOHpU-QhLXmuN2czQXRDAbFS3QAzdTkwNJ0x";
                        String message = "&message=" + sms;
                        String sender = "&sender=" + "TXTLCL";
                        String numbers = "&numbers=" + number;

                        // Send data
                        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                        String data = apiKey + numbers + message + sender;
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                        conn.getOutputStream().write(data.getBytes("UTF-8"));
                        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        final StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        rd.close();

                        return stringBuffer.toString();
                    } catch (Exception e) {
                        System.out.println("Error SMS "+e);
                        return "Error "+e;
                    }
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    System.out.println(s);
                    Toast.makeText(SendSMS.this, ""+s, Toast.LENGTH_SHORT).show();
                }
            }.execute();

        }
    }

}


