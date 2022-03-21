package com.sumitkaril.myserviceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForegroundServiceActivity extends AppCompatActivity {
    private Button startBTN, stopBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        startBTN = findViewById(R.id.btn_foreground_start_service);
        stopBTN = findViewById(R.id.btn_foreground_stop_service);
        onClickListenerMethods();
    }

    private void onClickListenerMethods() {
        startBTN.setOnClickListener(view -> {
            Intent serviceStartIntent = new Intent(ForegroundServiceActivity.this, ForegroundService.class);
            serviceStartIntent.putExtra("inputExtra", "Foreground Service Example in Android!!!");
            ContextCompat.startForegroundService(this, serviceStartIntent);
        });

        stopBTN.setOnClickListener(view -> {
            Intent serviceStopIntent = new Intent(this, ForegroundService.class);
            stopService(serviceStopIntent);
        });
    }
}