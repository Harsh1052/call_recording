package com.example.call_recording;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         final String[] permissions = {Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(this, permissions, 200);
    }
}