package com.yourfitonline.weatherforecast;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class JSONWeatherTask extends AsyncTask<String,Void,Weather> {

    final String LOG_TAG ="AppFilter";
    @Override
    protected Weather doInBackground(String ... params) {
        Weather weather = new Weather();
        String data = ( new Client().downloadInformation((String)params[0]));

        try {
            weather = JSONWeatherParser.getWeather(data);


            //weather.icon = ( (new Client()).getImage(weather.currentCondition.getIcon()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }



    protected void onPostExecute(Weather result) {
        super.onPostExecute(result);
        Log.d(LOG_TAG, "End. Result = " + result.getWindSpeed());

    }
}
