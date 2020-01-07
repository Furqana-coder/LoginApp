package com.example.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.InputStream;

public class StartActivity extends AppCompatActivity {
    Button button, button1,button2;
    static final int PICK_CONTACT_REQUEST = 1;
    private String TAG = "mainactivty";
    public String actualfilepath = "";
    private int request_code = 1, FILE_SELECT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
       // final Intent intent = new Intent(this, ByService.class);
        //startService(intent);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, NextActivity.class);
                startActivityForResult(intent, 2);
            }
        }
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
                } catch (Exception e) {
                    Log.e(TAG, " choose file error " + e.toString());
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
                startActivityForResult(intent1, 2);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, " result is "+ data + "  uri  "+ data.getData()+ " auth "+ data.getData().getAuthority()+ " path "+ data.getData().getPath());
        String fullerror = "";
        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {

                try {
                    Uri imageuri = data.getData();
                    InputStream stream = null;
                    String tempID = "", id = "";

                    Uri uri = data.getData();
                    //  Log.e(hi, "file auth is " + uri.getAuthority());
                    fullerror = fullerror + "file auth is " + uri.getAuthority();
                    if (imageuri.getAuthority().equals("media")) {
                        tempID = imageuri.toString();
                        tempID = tempID.substring(tempID.lastIndexOf("/") + 1);
                        id = tempID;
                        Uri contenturi = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        String selector = MediaStore.Images.Media._ID + "=?";
                        actualfilepath = getColunmData(contenturi, selector, new String[]{id});



                    }
                    File myFile = new File(actualfilepath);
                    // MessageDialog dialog = new MessageDialog(Home.this, " file details --"+actualfilepath+"\n---"+ uri.getPath() );
                    // dialog.displayMessageShow();
                    String temppath = uri.getPath();
                    if (temppath.contains("//")) {
                        temppath = temppath.substring(temppath.indexOf("//") + 1);
                    }
                    Log.e(TAG, " temppath is " + temppath);
                    fullerror = fullerror + "\n" + " file details -  " + actualfilepath + "\n --" + uri.getPath() + "\n--" + temppath;

                    if (actualfilepath.equals("") || actualfilepath.equals(" ")) {
                        myFile = new File(temppath);

                    } else {
                        myFile = new File(actualfilepath);
                    }

                    //File file = new File(actualfilepath);
                    //Log.e(TAG, " actual file path is "+ actualfilepath + "  name ---"+ file.getName());

//                    File myFile = new File(actualfilepath);
                    Log.e(TAG, " myfile is " + myFile.getAbsolutePath());
                    Toast.makeText(this, ""+myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    // lyf path  - /storage/emulated/0/kolektap/04-06-2018_Admin_1528088466207_file.xls

                } catch (Exception e) {
                    Log.e(TAG, " read errro " + e.toString());
                }
                //------------  /document/primary:kolektap/30-05-2018_Admin_1527671367030_file.xls
            }
        }
    }

    public String getColunmData(Uri uri, String selection, String[] selectarg) {

        String filepath = "";
        Cursor cursor = null;
        String colunm = "_data";
        String[] projection = {colunm};
        cursor = getContentResolver().query(uri, projection, selection, selectarg, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.e(TAG, " file path is " + cursor.getString(cursor.getColumnIndex(colunm)));
            filepath = cursor.getString(cursor.getColumnIndex(colunm));
        }
        if (cursor != null)
            cursor.close();
        return filepath;
    }

}

