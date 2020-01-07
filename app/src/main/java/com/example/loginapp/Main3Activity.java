package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.CountDownTimer;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.loginapp.RegisterActivity.PREFERENCE;
import static com.example.loginapp.SplashScreen.editor;
import static com.example.loginapp.SplashScreen.sh;
import static android.content.Context.MODE_PRIVATE;
public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private long backPressedTime;
    private Toast backToast;
    private static boolean userPressedBackAgain = false;
    Boolean doublebackpress = false;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Button load = (Button) findViewById(R.id.load);


        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
       /* load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prf = getSharedPreferences("user_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                final boolean commit = editor.commit();
                Intent intent = new Intent(Main3Activity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(intent);

                finish();
            }
        });

*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer;
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.








            int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {
            prf = getSharedPreferences("user_details", MODE_PRIVATE);
            SharedPreferences.Editor editor = prf.edit();
            editor.clear();
            final boolean commit = editor.commit();
            Intent intent = new Intent(Main3Activity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();

            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    Context context = this;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(context, Share.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent;
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_crud) {
            Intent intent = new Intent(context, MainActivity1.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_list) {
            Intent intent = new Intent(context, ListClick.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_retrofit) {
            Intent intent = new Intent(context, Retrofit.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_excel) {
            Intent intent = new Intent(context, Main2Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_json) {
            Intent intent = new Intent(context, UserActivity.class);
            startActivity(intent);}
            else if (id == R.id.nav_slideshow) {
                Intent intent = new Intent(context, Search.class);
                startActivity(intent);

        }

        else if (id == R.id.nav_rec) {
            Intent intent = new Intent(context, MainAct.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
           /* ApplicationInfo api = this.getApplicationInfo();
            String apkPath = "https://play.google.com/store";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "APP NAME (Open it in Google Play Store to Download the Application)");

            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, apkPath);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));*/
            Intent intent = new Intent(context, Gmail.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            /*SharedPreferences preferences =getSharedPreferences("loginTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
              editor.remove("loginTest");
            editor.commit();
            finish();*/
            Intent intent = new Intent(context, Contacts.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_sms) {
            /*SharedPreferences preferences =getSharedPreferences("loginTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
              editor.remove("loginTest");
            editor.commit();
            finish();*/
            Intent intent = new Intent(context, SendSMS.class);
            startActivity(intent);

        }
        else if (id == R.id.path) {
            /*SharedPreferences preferences =getSharedPreferences("loginTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
              editor.remove("loginTest");
            editor.commit();
            finish();*/
            Intent intent = new Intent(context, StartActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.notification) {
            /*SharedPreferences preferences =getSharedPreferences("loginTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
              editor.remove("loginTest");
            editor.commit();
            finish();*/
            Intent intent = new Intent(context, Notification.class);
            startActivity(intent);

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }



}
