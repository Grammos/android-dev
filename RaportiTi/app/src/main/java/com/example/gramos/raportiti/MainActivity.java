package com.example.gramos.raportiti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

    private final MyDBHandler myDbHandler = new MyDBHandler(this);


    private GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();


        Button button1 = (Button) findViewById(R.id.button1);

        //show submit button click event
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is -\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    myDbHandler.insertRecord(latitude, longitude);
                } else {


                    gps.showOneButtonDialog();
                }

            }
        });


    }

    private void OnClickButtonListener() {
        Button button0 = (Button) findViewById(R.id.button2);
        button0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.gramos.raportiti.MainActivity2");
                        startActivity(intent);
                    }
                }
        );


        Button button3 = (Button) findViewById(R.id.button);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        {


                            // O is not zero (0)!!!! O stands for object
                            List<DatabaseO> O = myDbHandler.getAllDataObCoo();
                            JSONArray jsonArray = new JSONArray();


                            try {
                                for (DatabaseO cn : O) {

                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("id", cn.getID());
                                    jsonObject.put("lat", cn.getLat());
                                    jsonObject.put("lng", cn.getLng());

                                    jsonArray.put(jsonObject);


                                }
                                Log.d("", jsonArray.toString());


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {
                                myDbHandler.close();
                            }


                            String url = "http://0.0.0.0:5000/geo-api";
                            // Create a new HttpClient and Post Header

                            HttpParams myParams = new BasicHttpParams();
                            HttpConnectionParams.setConnectionTimeout(myParams, 10000);
                            HttpConnectionParams.setSoTimeout(myParams, 10000);
                            HttpClient httpclient = new DefaultHttpClient(myParams);
                            String json = jsonArray.toString();

                            try {

                                HttpPost httppost = new HttpPost(url);
                                httppost.setHeader("Content-type", "application/json");

                                StringEntity se = new StringEntity(json);
                                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                                httppost.setEntity(se);

                                if (android.os.Build.VERSION.SDK_INT > 9) {
                                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                    StrictMode.setThreadPolicy(policy);
                                }


                                HttpResponse response = httpclient.execute(httppost);
                                String temp = EntityUtils.toString(response.getEntity());
                                Log.i("tag", temp);


                            } catch (ClientProtocolException e) {
                                e.printStackTrace();

                            } catch (IOException e) {
                                e.fillInStackTrace();
                            }


                            /**
                             // Create a new HttpClient and Post Header
                             HttpClient httpclient = new DefaultHttpClient();
                             String url = "http://0.0.0.0:5000/geo-api";


                             HttpPost httppost = new HttpPost(url);
                             httppost.setHeader("Content-type", "application/json");
                             StringEntity se = null;
                             try {
                             se = new StringEntity(jsonArray.toString());
                             } catch (UnsupportedEncodingException e) {
                             e.printStackTrace();
                             }
                             assert se != null;
                             se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                             httppost.setEntity(se);
                             try {
                             HttpResponse response = httpclient.execute(httppost);
                             String temp = EntityUtils.toString(response.getEntity());
                             Log.i("tag", temp);

                             } catch (IOException e) {
                             e.printStackTrace();

                             }*/


                            // System.out.println(jsonArray);
                            //return jsonArray ;

                        }



                    }
                }
        );

    }


}
