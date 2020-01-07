package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
public class PartTwoActivity extends AppCompatActivity {

        Toolbar toolbar;
        private static final String FILENAME = "data.txt";
        private static final String DNAME = "MYFILES";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_part_two);
            toolbar = (Toolbar) findViewById(R.id.toolbar1);
            final TextView textView=findViewById(R.id.textview1);
            Context context=this;
            File directory = context.getDir("mydir", Context.MODE_PRIVATE);
            Log.d("directory", directory.getAbsolutePath().toString());
            File folder = getFilesDir();
            File f= new File(folder, "doc_download");
            f.mkdir();
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!textView.getText().toString().isEmpty()) {
//                        File file = new File(PartTwoActivity.this.getFilesDir(), "text");
//                        if (!file.exists()) {
//                            file.mkdir();
//                            System.out.println(file);
//                        }
//                        try {
//                            File gpxfile = new File(file, "sample");
//                            FileWriter writer = new FileWriter(gpxfile);
//                            writer.flush();
//                            writer.close();
//                            Toast.makeText(PartTwoActivity.this, "Saved your text", Toast.LENGTH_LONG).show();
//                        } catch (Exception e) { }
//                    }
//
//                }
//            });

//            toolbar.setTitle("loginapp");
            toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarColor));
            setSupportActionBar(toolbar);
            File mFolder;
            mFolder = new File(Environment.getExternalStorageState(), "DDDD");
            if (!mFolder.exists()) {
                boolean b = mFolder.mkdirs();
                System.out.println("-----------"+b);
            }
//            File folder = getFilesDir();
//            File f= new File(folder, "doc_download");
//            System.out.println(f);
//            boolean b=f.mkdir();
//            System.out.println("Files in getFilesDir"  +b);
//            Context context=this;
//            File mydir = context.getDir("users", Context.MODE_PRIVATE); //Creating an internal dir;
//            if (!mydir.exists())
//            {
//                boolean b=mydir.mkdirs();
//                System.out.println(b);
//            }
//            String filename = "FileName.xlsx";
//            String extStorageDirectory = Environment.getExternalStorageDirectory()
//                    .toString();
//            File folder1 = new File(extStorageDirectory, "FolderName");// Name of the folder you want to keep your file in the local storage.
//            //creating the folder
//           if(!folder1.exists())
//           {
//               folder1.mkdir();
//           }
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
//                File file=new File(Environment.DIRECTORY_SCREENSHOTS,"hi");
//                System.out.println("ho folder"+file);
//                if(!file.exists()){
//                    file.mkdir();
//                }
//            }
            String path = Environment.getExternalStorageState()+ "/AAA/";
            System.out.println(path);
            File mFolder1 = new File(path);
            if (!mFolder1.exists()) {
                boolean b = mFolder1.mkdirs();
                System.out.println("+++++++++"+b);
            }
//            File dataFile = new File(rootPath, FILENAME);
//            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                Toast.makeText(this, "Cannot use storage.", Toast.LENGTH_SHORT).show();
//                finish();
//                return;
//            }
//            try {
//                FileOutputStream mOutput = new FileOutputStream(dataFile, false);
//                String data = "DATA";
//                mOutput.write(data.getBytes());
//                mOutput.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                FileInputStream mInput = new FileInputStream(dataFile);
//                byte[] data = new byte[128];
//                mInput.read(data);
//                mInput.close();
//
//                String display = new String(data);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            dataFile.delete();
//        }
        }
    }
