package com.example.gramos.raportiti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MyDBHandler myDbHandler = new MyDBHandler(this);

    Button button1, button0;



    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();


        button1 = (Button) findViewById(R.id.button1);

        //show submit button click event
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is -\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    myDbHandler.insertRecord(latitude, longitude);
                }else{


                    gps.showOneButtonDialog();
                }

            }
        });

    }

    public void OnClickButtonListener(){
        button0 = (Button) findViewById(R.id.button2);
        button0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.gramos.raportiti.MainActivity2");
                        startActivity(intent);
                    }
                }
        );

    }


}
