package com.yourfitonline.weatherforecast;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class ForecastListActivity extends AppCompatActivity {

    final String LOG_TAG = "AppFilter";
    final boolean isInternetAvailable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecastlist);
        Button btnAddCity = (Button)findViewById(R.id.btnAddCity);
        displayListView();
    }

    public void displayListView() {


        SimpleCursorAdapter dataAdapter;

        int[] to = new int[] {
                R.id.cityName,
                R.id.countryName,
                R.id.temperature,
                R.id.temperatureMax,
                R.id.temperatureMin,
                R.id.windSpeed,
                R.id.windDeg,
                R.id.cloudsProperties
        };

        ListView lvForecasts = (ListView) findViewById(R.id.lvForecasts);

        if(isInternetAvailable) {
            /*
            If internet online
            Recreate weatherlist table
            Create Json Task from cities table
            rewrite weatherlist
            assignment adapter

            If internet offline
            reading from weatherlist
            assignment adapter
             */

            DatabaseManager dbManager = DatabaseManager.getInstance(this);
            dbManager.createDB();



            Cursor c = dbManager.fetchAllWeathers();
            String[] data = {DatabaseManager.KEY_WEATHER_CITY,DatabaseManager.KEY_WEATHER_COUNTRY,
                    DatabaseManager.KEY_WEATHER_TEMP,DatabaseManager.KEY_WEATHER_TEMPMAX,
                    DatabaseManager.KEY_WEATHER_TEMPMIN,DatabaseManager.KEY_WEATHER_WINDSPEED,
                    DatabaseManager.KEY_WEATHER_WINDDEG,DatabaseManager.KEY_WEATHER_CLOUDSPERC};

            dataAdapter = new SimpleCursorAdapter(this,R.layout.item,c,data,to);
            lvForecasts.setAdapter(dataAdapter);

            dataAdapter.notifyDataSetChanged();
        }
    }
}
