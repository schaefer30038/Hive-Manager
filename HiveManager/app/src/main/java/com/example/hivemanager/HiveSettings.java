package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HiveSettings extends AppCompatActivity {
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_settings);

        Intent intent = getIntent();
        String extraString = intent.getStringExtra(Hive.EXTRA_TEXT);

        test = (TextView) findViewById(R.id.test_TextView);

        test.setText(extraString);
        // var test contains the hive name which we need to add/




    }
}