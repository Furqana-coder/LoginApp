package com.example.loginapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListClick extends AppCompatActivity {

    private String MARK = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get marks JSON
    private static String url = "https://api.github.com/users/octocat/repos";

    ArrayList<HashMap<String, String>> marksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_click);

        marksList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetMarks().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetMarks extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListClick.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(MARK, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray marks = new JSONArray(jsonStr);

                    // looping through All Marks
                    for (int i = 0; i < marks.length(); i++) {
                        JSONObject c = marks.getJSONObject(i);

                        String name = c.getString("name");
                        String full_name = c.getString("full_name");
                        String AP = c.getString("private");

                        // tmp hash map for single marks
                        HashMap<String, String> markes = new HashMap<>();

                        // adding each child node to HashMap key => value
                        markes.put("Name", name);
                        markes.put("full_name", full_name);
                        markes.put("PRIVATE", AP);

                        // adding marks to marks list
                        marksList.add(markes);
                    }
                } catch (final JSONException e) {
                    Log.e(MARK, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(MARK, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    ListClick.this, marksList,
                    R.layout.list_item, new String[]{"Name", "full_name","PRIVATE"},
                    new int[]{R.id.name,
                            R.id.marks,R.id.p});

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                    animation1.setDuration(4000);
                    view.startAnimation(animation1);
                    Toast.makeText(ListClick.this, "clicked  "+lv.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
//public class ListClick extends AppCompatActivity {
//
//    private String TAG = MainActivity.class.getSimpleName();
//
//    private ProgressDialog pDialog;
//    private ListView lv;
//
   //  URL to get contacts JSON
//    private static String url = "https://api.github.com/users/repos";
//
//    ArrayList<HashMap<String, String>> contactList;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_click);
//        new GetContacts().execute();
//    }
//
//    /**
//     * Async task class to get json by making HTTP call
//     */
//    private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
           //  Showing progress dialog
//            pDialog = new ProgressDialog(ListClick.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//        }
//        StringBuffer sb=new StringBuffer();
//        final ArrayList<String> messages = new ArrayList<String>();
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//
         //    Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);
//
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObjMain = new JSONObject(jsonStr);
//                    String id=jsonObjMain.getString("login");
//                    String name=jsonObjMain.getString("name");
//                    String node_id=jsonObjMain.getString("node_id");
//                    String avatar_url=jsonObjMain.getString("avatar_url");
//                    String html_url=jsonObjMain.getString("html_url");
//                    String created_at=jsonObjMain.getString("created_at");
//                    String gists_url=jsonObjMain.getString("gists_url");
//                    String subscriptions_url=jsonObjMain.getString("subscriptions_url");
//                    String repos_url=jsonObjMain.getString("repos_url");
//                    String events_url=jsonObjMain.getString("events_url");
//                    String received_events_url=jsonObjMain.getString("received_events_url");
//                    String type=jsonObjMain.getString("type");
//                    String url=jsonObjMain.getString("url");
//                    HashMap<String, String> contact = new HashMap<>();
//                    contact.put("id", id);
//                    System.out.println(""+contact);
                  //   JSONArray jsonArray = jsonObjMain.getJSONArray("events");
//                    messages.add(id);
//                    messages.add(name);
//                    messages.add(node_id);
//                    messages.add(url);
//                    messages.add(avatar_url);
//                    messages.add(html_url);
//                    messages.add(created_at);
//                    messages.add(gists_url);
//                    messages.add(subscriptions_url);
//                    messages.add(repos_url);
//                    messages.add(events_url);
//                    messages.add(received_events_url);
//                    messages.add(type);
//
//                    /*
//                    * "followers_url": "https://api.github.com/users/repos/followers",
//  "following_url": "https://api.github.com/users/repos/following{/other_user}",
//  "gists_url": "https://api.github.com/users/repos/gists{/gist_id}",
//  "starred_url": "https://api.github.com/users/repos/starred{/owner}{/repo}",
//  "subscriptions_url": "https://api.github.com/users/repos/subscriptions",
//  "organizations_url": "https://api.github.com/users/repos/orgs",
//  "repos_url": "https://api.github.com/users/repos/repos",
//  "events_url": "https://api.github.com/users/repos/events{/privacy}",
//  "received_events_url": "https://api.github.com/users/repos/received_events",
//  "type": "User",
//  "site_admin": false,
//  "name": null,
//  "company": null,
//  "blog": "",
//  "location": null,
//  "email": null,
//  "hireable": null,
//  "bio": null,
//  "public_repos": 0,
//  "public_gists": 0,
//  "followers": 18,
//  "following": 0,
//  "created_at": "2009-04-24T13:53:27Z",
//  "updated_at": "2014-03-09T10:18:05Z"*/
//
//
//
//
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });
//
//                }
//
//
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);

//            if (pDialog.isShowing())
//                pDialog.dismiss();
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListClick.this,
//                    android.R.layout.simple_list_item_1, messages);
//            final ListView list = (ListView) findViewById(R.id.list_notice2);
//            list.setAdapter(adapter);
//            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//                    Toast.makeText(ListClick.this, "clicked  "+messages.get(position).toString(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//        }
//
//    }
//}