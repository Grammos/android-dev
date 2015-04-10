package com.example.gramos.raportiti;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener, LocationListener {


    Button mbutton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mbutton1 = (Button) findViewById(R.id.button1);
        mbutton1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

                showOneButtonDialog();
    }

    private void showOneButtonDialog(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10,this);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("GPS is required");
        dialogBuilder.setMessage("Turn on the GPS! Please pres the Settings");
        dialogBuilder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        if(LocationManager.GPS_PROVIDER.equals(s)){
            Toast.makeText(this,"GPS on",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String s) {
        if(LocationManager.GPS_PROVIDER.equals(s)){
            Toast.makeText(this,"GPS off",Toast.LENGTH_SHORT).show();
        }

    }
}
