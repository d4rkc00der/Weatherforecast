package com.yourfitonline.weatherforecast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class DatabaseManager {



    public static final String KEY_CITY_ROWID = "_id";
    public static final String KEY_CITY_NAME = "city";
    public static final String KEY_CITY_LON = "lon";
    public static final String KEY_CITY_LAT = "lat";

    public static final String KEY_WEATHER_ROWID = "_id";
    public static final String KEY_WEATHER_COUNTRY = "country";
    public static final String KEY_WEATHER_CITY = "city";
    public static final String KEY_WEATHER_WINDSPEED = "windSpeed";
    public static final String KEY_WEATHER_WINDDEG = "windDeg";
    public static final String KEY_WEATHER_TEMPMAX = "temp_max";
    public static final String KEY_WEATHER_TEMPMIN = "temp_min";
    public static final String KEY_WEATHER_TEMP = "temp";
    public static final String KEY_WEATHER_CLOUDSPERC = "cloudPerc";
    private Context context;
    private final String DB_NAME = "WeatherDB";
    private static final String LOG_TAG = "AppFilter";
    private DBHelper dbh;
    private SQLiteDatabase db;


    public static boolean isDatbaseManagerInitialized = false;

    public static DatabaseManager getInstance(Context con) {
        if(!isDatbaseManagerInitialized){
            return new DatabaseManager(con);
        } else {
            Log.d(LOG_TAG,"Tried to get Another instance of database manager");
            return null;
        }
    }

    private DatabaseManager(Context context){
        this.context = context;
        dbh = new DBHelper(context);

    }

    public boolean isDataBaseExist(){

        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            Log.d(LOG_TAG,"Database found");
            checkDB.close();
        } catch (SQLiteException e) {
            Log.d(LOG_TAG,"Database not found");
            return false;
        }
        return checkDB != null;
    }

    public void writeToDb(Weather weather) {

        db = dbh.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("country ",weather.getLocation().getCountry());
        cv.put("city ",weather.getLocation().getCity());
        cv.put("temp",weather.getTemp());
        cv.put("temp_max",weather.getTemp_max());
        cv.put("temp_min",weather.getTemp_min());
        cv.put("humidity",weather.getHumidity());
        cv.put("condition",weather.getCondition());
        cv.put("pressure",weather.getPressure());
        cv.put("cloudPerc",weather.getCloudPerc());
        cv.put("windSpeed",weather.getWindSpeed());
        cv.put("windDeg",weather.getWindDeg());
        cv.put("descr",weather.getDescr());
        cv.put("weatherId",weather.getWeatherId());
        cv.put("icon",weather.getIcon());
        db.insert("weatherlist", null, cv);
        Log.d(LOG_TAG, "Writed " + cv.size() + " records into weatherlist table");
        db.close();

    }

    public void updateWeather(Weather weather){

        Cursor c = fetchAllCities();


        if (c != null) {
            if (c.moveToFirst()) {
                String lon,lat,location;

                do {
                    lon = "";
                    lat = "";

                    lon = lon.concat(c.getString(2));
                    lat = lat.concat(c.getString(3));

                    Log.d(LOG_TAG, lon + ":" + lat);
                    location = "lat=" + lat + "&lon=" + lon;
                    try {
                        JSONWeatherTask task = new JSONWeatherTask();
                        task.execute(new String[]{location});
                        weather = task.get();
                        writeToDb(weather);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } while (c.moveToNext());
            }
        } else {

        }
    }
    public void createDB(){
        DBHelper dbh = new DBHelper(context);
        SQLiteDatabase database = dbh.getWritableDatabase();
        database.close();
        Log.d(LOG_TAG,"create DB " + DB_NAME);
    }

    public Cursor fetchAllWeathers() {
        db = dbh.getReadableDatabase();
        Cursor mCursor = db.query("weatherlist", new String[] {KEY_WEATHER_ROWID,KEY_WEATHER_COUNTRY,KEY_WEATHER_CITY,
                        KEY_WEATHER_WINDSPEED,KEY_WEATHER_WINDDEG,KEY_WEATHER_TEMPMAX,KEY_WEATHER_TEMPMIN,
                        KEY_WEATHER_TEMP,KEY_WEATHER_CLOUDSPERC},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public Cursor fetchAllCities() {
        db = dbh.getReadableDatabase();
        Cursor mCursor = db.query("cities", new String[] {KEY_CITY_ROWID,KEY_CITY_NAME,KEY_CITY_LON, KEY_CITY_LAT},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");


            // Table Cities
            db.execSQL("create table if not exists cities ("
                            + "_id integer primary key autoincrement,"
                            + "city text,"
                            + "lon float,"
                            + "lat float" + ");");
            ContentValues cv = new ContentValues();
            cv.put("city","London");
            cv.put("lon",-0.12574);
            cv.put("lat",51.50853);
            db.insert("cities",null, cv);


            db.execSQL("drop table if exists weatherlist;");
            // Weather records table
            db.execSQL("create table weatherlist ("
                    + "_id integer primary key autoincrement,"
                    + "city text,"
                    + "lon float,"
                    + "lat float,"
                    + "country text,"
                    + "weatherId integer,"
                    + "descr text,"
                    + "condition text,"
                    + "icon text,"
                    + "humidity integer,"
                    + "windSpeed float,"
                    + "windDeg float,"
                    + "pressure integer,"
                    + "temp float,"
                    + "temp_max float,"
                    + "temp_min float,"
                    + "cloudPerc integer" + ");");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists weatherlist;");
            // Weather records table
            db.execSQL("create table weatherlist ("
                    + "_id integer primary key autoincrement,"
                    + "city text,"
                    + "lon float,"
                    + "lat float,"
                    + "country text,"
                    + "weatherId integer,"
                    + "descr text,"
                    + "condition text,"
                    + "icon text,"
                    + "humidity integer,"
                    + "windSpeed float,"
                    + "windDeg float,"
                    + "pressure integer,"
                    + "temp float,"
                    + "temp_max float,"
                    + "temp_min float,"
                    + "cloudPerc integer" + ");");


        }
    }
}
