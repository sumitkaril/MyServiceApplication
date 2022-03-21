package com.sumitkaril.myserviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectServiceActivity extends AppCompatActivity {
    private Button backgroundServiceBTN, foregroundServiceBTN, boundServiceBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
        backgroundServiceBTN = findViewById(R.id.btn_background_service);
        foregroundServiceBTN = findViewById(R.id.btn_foreground_service);
        boundServiceBTN = findViewById(R.id.btn_bound_service);
        onClickListenerMethods();
    }

    private void onClickListenerMethods() {
        backgroundServiceBTN.setOnClickListener(view -> startActivity(new Intent(SelectServiceActivity.this, MainActivity.class)));

        foregroundServiceBTN.setOnClickListener(view -> startActivity(new Intent(SelectServiceActivity.this, ForegroundServiceActivity.class)));

        boundServiceBTN.setOnClickListener(view -> startActivity(new Intent(SelectServiceActivity.this, BoundServiceActivity.class)));
    }
}