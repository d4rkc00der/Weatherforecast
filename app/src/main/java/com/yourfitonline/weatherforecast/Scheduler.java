package com.yourfitonline.weatherforecast;

import android.content.Context;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgenyshumakov on 11.10.16.
 */

public class Scheduler {

    final String LOG_TAG = "AppFilter";
    long timeInterval;
    public Scheduler(){}

    public void setSchedule(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public void startScheduler(final JSONWeatherTask task, final Context context){
        final DatabaseManager databaseManager = DatabaseManager.getInstance(context);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                    try {
                        databaseManager.updateWeather(task.get());

                    } catch (Exception e){
                        e.printStackTrace();
                    }



            }
        },0L,5L * 1000);

    }
    public void stopScheduler(){

    }


}
