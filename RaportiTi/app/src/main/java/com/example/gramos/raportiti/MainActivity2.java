package com.example.gramos.raportiti;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        ActionBar appCompatActivity = getSupportActionBar();
        createTable();
        if (appCompatActivity != null) {
            appCompatActivity.setHomeButtonEnabled(true);
            appCompatActivity.setDisplayHomeAsUpEnabled(true);
        }




    }

    private void createTable() {
        /* All the Tables would be attached to the tableLayout body */
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        /*First row that would be used as a header and would never change */
        TableRow tableRow0 = new TableRow(this);

        // First text view that would be as a "column1" ID
        TextView tv0 = new TextView(this);
        tv0.setText("ID");
        tv0.setTextColor(Color.WHITE);
        tv0.setGravity(Gravity.LEFT);
        tv0.setPadding(5, 5, 5, 5);
        tableRow0.addView(tv0);

        // that would be as a "column2" LATITUDE
        TextView tv1 = new TextView(this);
        tv1.setText(" LATITUDE");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.WHITE);
        tv1.setPadding(5, 5, 5, 5);
        tableRow0.addView(tv1);

        // that would be as a "column3" LONGITUDE
        TextView tv2 = new TextView(this);
        tv2.setText(" LONGITUDE ");
        tv2.setGravity(Gravity.LEFT);
        tv2.setTextColor(Color.WHITE);
        tableRow0.addView(tv2);
        // added this row in the tableLayout body
        stk.addView(tableRow0);


        MyDBHandler myDBHandler = new MyDBHandler(this);

        // O is not zero (0)!!!! O stands for object
        List<DatabaseO> O = myDBHandler.getAllDataObCoo();

        for (DatabaseO cn : O) {

            // Second table row that would change every time would be dynamically change
            TableRow tableRow = new TableRow(this);

            TextView t1v = new TextView(this);
            t1v.setText(Integer.toString(cn.getID()));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.LEFT);
            tableRow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(Double.toString(cn.getLat()));
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tableRow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(Double.toString(cn.getLng()));
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.LEFT);
            tableRow.addView(t3v);

            stk.addView(tableRow);
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