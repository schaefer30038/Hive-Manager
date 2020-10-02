package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HiveSettings extends AppCompatActivity implements View.OnClickListener{
    TextView hiveName_TextView, hiveApiary_TextView, hiveInfo_TextView,  username_TextView, apiary_TextView, hive_TextView, inspection_TextView, health_TextView, honey_TextView,
             queenProduction_TextView, equipHive_TextView,equipInven_TextView, loss_TextView, gain_TextView;
    Button deleteHive_Button, editHive_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_settings);

        // Retrieves the hive name, or if creating a new hive, is NULL
        Intent intent = getIntent();
        String stringHiveName = intent.getStringExtra(Hive.EXTRA_TEXT_HIVE);
        String stringApiaryName = intent.getStringExtra(Hive.EXTRA_TEXT_APIARY);

        deleteHive_Button = (Button)findViewById(R.id.deleteHive_Button);
        editHive_Button = (Button)findViewById(R.id.editHive_Button);

        hiveInfo_TextView = (TextView)findViewById(R.id.hiveInfo_TextView);
        username_TextView = (TextView)findViewById(R.id.username_TextView);
        apiary_TextView = (TextView)findViewById(R.id.apiary_TextView);
        hive_TextView = (TextView)findViewById(R.id.hive_TextView);
        inspection_TextView = (TextView)findViewById(R.id.inspection_TextView);
        health_TextView = (TextView)findViewById(R.id.health_TextView);
        honey_TextView = (TextView)findViewById(R.id.honey_TextView);
        queenProduction_TextView = (TextView)findViewById(R.id.queenProduction_TextView);
        equipHive_TextView = (TextView)findViewById(R.id.equipHive_TextView);
        equipInven_TextView = (TextView)findViewById(R.id.equipInven_TextView);
        loss_TextView = (TextView)findViewById(R.id.loss_TextView);
        gain_TextView = (TextView)findViewById(R.id.gain_TextView);

        // TODO set each TextView to display current hive information
        //username_TextView.setText("");

        deleteHive_Button.setOnClickListener(this);
        editHive_Button.setOnClickListener(this);

        //hiveName_TextView = (TextView) findViewById(R.id.hiveName_TextView);
        //hiveApiary_TextView = (TextView) findViewById(R.id.hiveName_TextView);

        //hiveName_TextView.setText(stringHiveName);
        //hiveApiary_TextView.setText(stringApiaryName);

        // var test contains the hive name which we need to add




    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.deleteHive_Button:
                // TODO DON'T FORGET TO DELETE THE HIVE
                break;

            case R.id.editHive_Button:
                // TODO DON'T FORGET TO UPDATE THE HIVE
                break;

        }
        // After button click, send to Hive page
        Intent intent2HiveSettings = new Intent(HiveSettings.this, Hive.class);
        startActivity(intent2HiveSettings);
    }
}