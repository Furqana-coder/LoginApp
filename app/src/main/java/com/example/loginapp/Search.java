package com.example.loginapp;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.loginapp.DatabaseHelper.TABLE_NAME;


public class Search extends Activity {
    com.example.loginapp.DatabaseHelper databaseHelper;
    EditText editMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText editName = (EditText) findViewById(R.id.editName);
        final EditText editEmail = (EditText) findViewById(R.id.editEmail);

        Button Search;
        databaseHelper = new DatabaseHelper(this);
        Search = (Button) findViewById(R.id.Search);
        editMobile = (EditText) findViewById(R.id.editMobile);
            Search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editMobile.length() == 0){

                                Toast.makeText(getApplicationContext(), "please enter the number", Toast.LENGTH_SHORT).show();
                                return ;}
                           Cursor cursor= databaseHelper.getData(editMobile.getText().toString());
                            if (cursor.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();

                            while (cursor.moveToNext())
                            {
                                //editName.setText(cursor.getString(1));
                               // editEmail.setText(cursor.getString(2));
                                buffer.append("Name :" + cursor.getString(1) + "\n");
                                buffer.append("Email :" + cursor.getString(2) + "\n");
                            }
                           /* Cursor res = myDb.getAllData();
                            if (res.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                               // buffer.append("Id :" + res.getString(0) + "\n");
                                buffer.append("Name :" + res.getString(1) + "\n");
                                buffer.append("Email :" + res.getString(2) + "\n");
                              //  buffer.append("Mobile Number :" + res.getString(3) + "\n\n");
                            }*/

                            // Show all data
                            showMessage("Data", buffer.toString());



                        }


                    }


            );
        }




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


