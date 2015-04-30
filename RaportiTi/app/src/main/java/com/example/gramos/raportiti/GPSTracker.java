package com.example.gramos.raportiti;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import static android.location.LocationManager.GPS_PROVIDER;



@SuppressLint("Registered")
public class GPSTracker extends Service implements LocationListener {

    private final Context aContext;


    // GPS status
    private boolean canGetLocation = false;
    private Location location; // location(lokacioni)
    private double latitude; // latitude(gjeresia)
    private double longitude; // longitude(gjatesia)

    // The minimum distance to change Updates in meters (distanca minimale per me e ba update coor.)
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds (koha minimale qe duhet me ba update)
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

    public GPSTracker(Context context){
        this.aContext = context;
        getLocation();

    }

    @SuppressWarnings("UnusedReturnValue")
    private Location getLocation(){

        try {
            LocationManager locationManager = (LocationManager) aContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            //noinspection StatementWithEmptyBody
            if(!isGPSEnabled && !isNetworkEnabled){
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
               if (isNetworkEnabled) {
                   locationManager.requestLocationUpdates(
                           LocationManager.NETWORK_PROVIDER,
                           MIN_TIME_BW_UPDATES,
                           MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                   Log.d("Network", "Network");
                   location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                   if (location != null){
                       latitude = location.getLatitude();
                       longitude = location.getLongitude();

                   }
                   // if GPS Enabled get lat/long using GPS services
               if(isGPSEnabled) {
                   if (location == null) {
                       locationManager.requestLocationUpdates(
                               LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                               MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                       Log.d("GPS Enabled", "GPS Enabled");
                       location = locationManager.getLastKnownLocation(GPS_PROVIDER);
                       if (location != null) {
                           latitude = location.getLatitude();
                           longitude = location.getLongitude();
                       }

                   }
               }

               }

            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return  location;

    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     *
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }
     */

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
    * Function to show settings alert dialog
     * On pressing Settings button will launch Settings Options
    **/

    public void showOneButtonDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(aContext);
        // setting dialog title
        dialogBuilder.setTitle("GPS is required!");

        // setting dialog message
        dialogBuilder.setMessage("Please, go to the Settings button. Hurry up :)!! ");

        dialogBuilder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                aContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // showing alert message
        dialogBuilder.show();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
