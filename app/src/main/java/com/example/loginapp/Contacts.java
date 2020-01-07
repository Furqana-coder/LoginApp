package com.example.loginapp;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Contacts extends AppCompatActivity implements ListView.OnItemClickListener {

    private static final int RESULT_PICK_CONTACT = 1;
    public String tag = "MainActivity";
    DatabaseContacts myDb;
    ListView listView;
    SearchView SearchView;
    final ArrayList selectedItem=new ArrayList();

    CheckedTextView CheckedTextView;
    boolean[] checkedItems;

    ArrayList<String> StoreContacts = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> ContactList = new ArrayList<>();
    Cursor cursor;
    String name, phonenumber;
    public static final int RequestPermissionCode = 1;
    public static final String Tag = "hello";
    Button button, select, load,loaddb;
    EditText name1, number;
    private BreakIterator mItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<String> arrayList = new ArrayList<>();
        setContentView(R.layout.activity_contacts);
        myDb=new DatabaseContacts(this);
        listView = (ListView) findViewById(R.id.listview1);
        //  CheckedTextView = (CheckedTextView) findViewById(R.id.view);

        button = (Button) findViewById(R.id.button1);
        load = (Button) findViewById(R.id.load);
        select = (Button) findViewById(R.id.select);
         loaddb  = (Button) findViewById(R.id.loaddb);

        SearchView = (SearchView) findViewById(R.id.searchView);
        StoreContacts = new ArrayList<String>();


        EnableRuntimePermission();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetContactsIntoArrayList();
               //


                arrayAdapter = new ArrayAdapter<String>(
                        Contacts.this,
                        R.layout.contact_items_listview,
                        R.id.textView, StoreContacts

                );


                listView.setAdapter(arrayAdapter);
                // myDb.insertData(name,phonenumber);
                //listView.setOnItemClickListener(this);


            }
        });
        loaddb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new WordLoaderTask().execute();
            }});


                select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ContactList.isEmpty()) {

                    SmsManager sms = SmsManager.getDefault();

                    String message = "Hello";

                    for (String number : ContactList) {
                        sms.sendTextMessage(number, null, message, null, null);
                    }
                    Toast.makeText(Contacts.this, "Message send to" + ContactList, Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(Contacts.this, "select", Toast.LENGTH_SHORT).show();
                }
            }
        });


        listView.setOnItemClickListener(this);
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GetContactsIntoDb();
                if (StoreContacts.contains(query)) {
                    arrayAdapter.getFilter().filter(query);
                } else {
                    Toast.makeText(Contacts.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }


        });
        load.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("NAME :" + res.getString(1) + "\n");
                            buffer.append("NUMBER :" + res.getString(2) + "\n");
                           // buffer.append("Mobile Number :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }


                });


    }


    public void showMessage(String title, final String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
       builder.setMessage(Message);


        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                for (int i = 0; i < selectedItem.size(); i++) {
                    item = item + selectedItem.get(i);
                    if (i != selectedItem.size() - 1) {
                        item = item + ", ";
                    }
                }
            }
        });builder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });builder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    selectedItem.clear();
                    mItemSelected.setText("");
                }
            }
        });

        builder.show();
    }






    public void GetContactsIntoArrayList() {

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " " + ":" + " " + phonenumber);
            //  StoreContacts.add(phonenumber);
            //myDb.insertData(name,phonenumber);
          //  myDb.insertData(name,phonenumber);
           // Log.d("list", myDb.toString());

        }
        if (cursor != null) {
            cursor.close();
        }




    }

    public void GetContactsIntoDb() {

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            //StoreContacts.add(name + " " + ":" + " " + phonenumber);
            //  StoreContacts.add(phonenumber);
            //myDb.insertData(name,phonenumber);
            myDb.insertData(name, phonenumber);
            Log.d("list", myDb.toString());

        }
        if (cursor != null) {
            cursor.close();
        }
    }
    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                Contacts.this,
                Manifest.permission.READ_CONTACTS)) {

            Toast.makeText(Contacts.this, "CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Contacts.this, new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    Contacts.this,
                    Manifest.permission.READ_SMS)) {

                Toast.makeText(Contacts.this, "CONTACTS permission allows us to Access Messages app", Toast.LENGTH_LONG).show();

            } else {

                ActivityCompat.requestPermissions(Contacts.this, new String[]{
                        Manifest.permission.READ_SMS}, RequestPermissionCode);

            }
        }




    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Contacts.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Contacts.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


   @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {
        int flag=1;
        String contactnumber=StoreContacts.get(j);
       Toast.makeText(this, "Clicked " +contactnumber, Toast.LENGTH_SHORT).show();
       ContactList.add(contactnumber);
      //myDb.insertData(contactnumber);
       Log.d("list", ContactList.toString());
       Toast.makeText(this, "Clicked " + ContactList, Toast.LENGTH_SHORT).show();
    }
    class WordLoaderTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {
            GetContactsIntoDb();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}