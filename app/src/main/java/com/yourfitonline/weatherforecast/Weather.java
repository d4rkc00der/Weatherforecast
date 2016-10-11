package com.yourfitonline.weatherforecast;

import android.location.Location;

import org.json.JSONObject;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class Weather {

    private byte[] image = null;
    private int weatherId;
    private String descr;
    private String condition;
    private String icon;
    private int humidity;
    private float windSpeed;
    private float windDeg;
    private int pressure;
    private double temp_max;
    private double temp_min;
    private double temp;
    private int cloudPerc;



    public City location;

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDeg(float windDeg) {
        this.windDeg = windDeg;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setMaxTemp(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setMinTemp(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setCloudPerc(int cloudPerc) {
        this.cloudPerc = cloudPerc;
    }

    public void setWeatherId(int weatherId){
        this.weatherId = weatherId;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }

    public byte[] getImage() {
        return image;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getDescr() {
        return descr;
    }

    public String getCondition() {
        return condition;
    }

    public String getIcon() {
        return icon;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDeg() {
        return windDeg;
    }

    public int getPressure() {
        return pressure;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp() {
        return temp;
    }

    public int getCloudPerc() {
        return cloudPerc;
    }

    public City getLocation() {
        return location;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }


    public Weather() {
    }



}
