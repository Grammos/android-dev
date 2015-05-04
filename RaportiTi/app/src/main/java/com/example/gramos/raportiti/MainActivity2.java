package com.example.gramos.raportiti;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        ActionBar appCompatActivity = getSupportActionBar();

        if (appCompatActivity != null) {
            appCompatActivity.setHomeButtonEnabled(true);
            appCompatActivity.setDisplayHomeAsUpEnabled(true);
        }

        MyDBHandler myDBHandler = new MyDBHandler(this);


        // Reading all Coordinates
        Log.d("Reading: ", "Reading all data ..");

        // O is not zero (0)!!!! O stands for object
        List<DatabaseO> O = myDBHandler.getAllDataObCoo();

        for (DatabaseO cn : O) {
            String log = "Id: " + cn.getID() + ", lat: " + cn.getLat() + " long:" + cn.getLng();

            Log.d("database0: ", log);
        }

        TextView t1 = (TextView) findViewById(R.id.id1);
        TextView t2 = (TextView) findViewById(R.id.lat1);
        TextView t3 = (TextView) findViewById(R.id.lng1);

        for (DatabaseO cn : O) {

            t1.setText(Integer.toString(cn.getID()));
            t2.setText(Double.toString(cn.getLat()));
            t3.setText(Double.toString(cn.getLng()));

        }





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        //return true;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity2, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);

    }
}