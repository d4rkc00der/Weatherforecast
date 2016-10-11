package com.yourfitonline.weatherforecast;

import android.location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.data;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class JSONWeatherParser {

    static Weather weather = null;
    public JSONWeatherParser() {

    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
    public static Weather getWeather(String data){
        weather = new Weather();

        City loc = new City();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject coordObj = getObject("coord", jsonObject);
            loc.setLatitude(getFloat("lat", coordObj));
            loc.setLongitude(getFloat("lon", coordObj));

            JSONObject sysObj = getObject("sys", jsonObject);
            loc.setCountry(getString("country", sysObj));
            loc.setSunrise(getInt("sunrise", sysObj));
            loc.setSunset(getInt("sunset", sysObj));
            loc.setCity(getString("name", jsonObject));
            weather.location = loc;

            JSONArray jArr = jsonObject.getJSONArray("weather");


            JSONObject JSONWeather = jArr.getJSONObject(0);
            weather.setWeatherId(getInt("id", JSONWeather));
            weather.setDescr(getString("description", JSONWeather));
            weather.setCondition(getString("main", JSONWeather));
            weather.setIcon(getString("icon", JSONWeather));

            JSONObject mainObj = getObject("main", jsonObject);
            weather.setHumidity(getInt("humidity", mainObj));
            weather.setPressure(getInt("pressure", mainObj));
            weather.setMaxTemp(getFloat("temp_max", mainObj)- 273.15);
            weather.setMinTemp(getFloat("temp_min", mainObj)- 273.15);
            weather.setTemp(getFloat("temp", mainObj)- 273.15);

            JSONObject wObj = getObject("wind", jsonObject);
            weather.setWindSpeed(getFloat("speed", wObj));
            weather.setWindDeg(getFloat("deg", wObj));

            JSONObject cObj = getObject("clouds", jsonObject);
            weather.setCloudPerc(getInt("all", cObj));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
