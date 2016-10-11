package com.yourfitonline.weatherforecast;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class City {
    private float longitude;
    private float latitude;
    private int sunrise;
    private int sunset;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    private String city;
    private String country;

    public City(){
        longitude = 0;
        sunrise = 0;
        sunset = 0;

    }

    public void setLatitude(float lat) {
        this.latitude = lat;
    }



    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
