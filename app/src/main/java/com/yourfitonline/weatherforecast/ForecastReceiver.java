package com.yourfitonline.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by evgenyshumakov on 10.10.16.
 */

public class ForecastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,WeatherService.class));
    }
}
