package com.yourfitonline.weatherforecast;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.util.Log;

import java.io.InputStream;

public class WeatherService extends IntentService {
    final String LOG_TAG = "AppFilter";


    public WeatherService() {
        super("name");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DatabaseManager databaseManager = DatabaseManager.getInstance(this);


        if(databaseManager.isDataBaseExist()) {
            if(isInternetAvailable()) {
                databaseManager.createDB();
            }
        }


        if(isInternetAvailable()) {
            JSONWeatherTask task = new JSONWeatherTask();
            Scheduler scheduler = new Scheduler();

            scheduler.startScheduler(task,this);


        }

        return super.onStartCommand(intent, flags, startId);
    }


    public boolean isInternetAvailable(){
        return true;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.d(LOG_TAG,"onHanleIntent");
    }

}
