package com.sumitkaril.myserviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

public class BoundServiceActivity extends AppCompatActivity {
    private Button printStampBTN, stopBTN;
    private TextView timestampTV;
    boolean mServiceBound = false;
    private BoundService boundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        timestampTV = findViewById(R.id.tv_time_stamp);
        printStampBTN = findViewById(R.id.btn_start_bound_service_print_timestamp);
        stopBTN = findViewById(R.id.btn_stop_bound_service);

        onClickListenerMethods();
    }

    private void onClickListenerMethods() {
        printStampBTN.setOnClickListener(view -> {
            if (mServiceBound){
                timestampTV.setText(boundService.getTimeStamp());
            }
        });

        stopBTN.setOnClickListener(view -> {
            if (mServiceBound){
                unbindService(serviceConnection);
                mServiceBound = false;
            }
            Intent intent = new Intent(BoundServiceActivity.this, BoundService.class);
            stopService(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = (new Intent(this, BoundService.class));
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound){
            unbindService(serviceConnection);
            mServiceBound = false;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceBound = false;
        }
    };
}