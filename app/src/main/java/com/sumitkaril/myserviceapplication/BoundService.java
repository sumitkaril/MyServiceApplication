package com.sumitkaril.myserviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

public class BoundService extends Service {
    private static String LOG_TAG = "BoundService";
    private IBinder mBinder = new MyBinder();
    private Chronometer chronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chronometer.stop();
    }

    public String getTimeStamp(){
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elapsedMillis/3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000)/60000;
        int seconds =(int) (elapsedMillis-hours*3600000 - minutes * 60000)/1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours+ ":" +minutes+ ":"+seconds+":"+millis;
    }

    public class MyBinder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }
    }
}
