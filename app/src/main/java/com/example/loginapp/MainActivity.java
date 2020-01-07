package com.example.loginapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import android.support.v7.app.ActionBarActivity;


public class MainActivity extends Activity {
    DatabaseHelper myDb;
    EditText editName, editEmail, editMobile, editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete,button_viewAll;

    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editMobile = (EditText) findViewById(R.id.editMobile);
        //  editTextId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        //btnviewAll = (Button) findViewById(R.id.button_viewAll);
       // btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
      //  viewAll();
       // UpdateData();
        DeleteData();
    }





    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editMobile.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

   /* public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editMobile.getText().toString(),
                                editName.getText().toString(),
                                editEmail.getText().toString(), editMobile.getText().toString());
                        if (isUpdate == true) {
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-zA-Z]+";
                        String Pattern = "[0-9]{10}";
                        if (validate()) {
                            if (!editMobile.getText().toString().matches(Pattern)) {
                                Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                            }
                            else{ boolean isInserted = myDb.insertData(editName.getText().toString(),
                                    editEmail.getText().toString(),
                                    editMobile.getText().toString());
                                if (isInserted == true)
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                            }
                        }
                    }


                    public boolean validate() {

                        String MobilePattern = "[0-9]{10}";
                        //String email1 = email.getText().toString().trim();
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]+";

                        if (editName.length() > 25) {

                            Toast.makeText(getApplicationContext(), "pls enter less the 25 character in user name", Toast.LENGTH_SHORT).show();
                            return false;

                        } else if (editName.length() == 0 || editMobile.length() == 0 || editName.length() ==
                                0) {

                            Toast.makeText(getApplicationContext(), "pls fill the empty fields", Toast.LENGTH_SHORT).show();
                            return false;

                        } else if (editEmail.getText().toString().matches(emailPattern)) {

                            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                            return true;

                        } else if (!editEmail.getText().toString().matches(emailPattern)) {

                            Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                            return false;

                        }


                        return false;

                    }
                }
        );
    }




    /*public void viewAll() {
        btnviewAll.setOnClickListener(
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
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Email :" + res.getString(2) + "\n");
                            buffer.append("Mobile Number :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }*/

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}