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
    Button deleteHive_Button, editHive_Button, createHive_Button;

    static String stringHiveName, stringApiaryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_settings);

        // Retrieves the hive name, or if creating a new hive, is NULL
        Intent intent = getIntent();
        stringHiveName = intent.getStringExtra(Hive.EXTRA_TEXT_HIVE);
        stringApiaryName = intent.getStringExtra(Hive.EXTRA_TEXT_APIARY);

        deleteHive_Button = (Button)findViewById(R.id.deleteHive_Button);
        editHive_Button = (Button)findViewById(R.id.editHive_Button);
        createHive_Button = (Button)findViewById(R.id.createHive_Button);

        hiveInfo_TextView = (TextView)findViewById(R.id.hiveInfo_TextView);
        username_TextView = (TextView)findViewById(R.id.username_TextView);
        apiary_TextView = (TextView)findViewById(R.id.apiary_TextView);

        // These are edit-able by the user
        hive_TextView = (TextView)findViewById(R.id.hive_TextView);
        inspection_TextView = (TextView)findViewById(R.id.inspection_TextView);
        health_TextView = (TextView)findViewById(R.id.health_TextView);
        honey_TextView = (TextView)findViewById(R.id.honey_TextView);
        queenProduction_TextView = (TextView)findViewById(R.id.queenProduction_TextView);
        equipHive_TextView = (TextView)findViewById(R.id.equipHive_TextView);
        equipInven_TextView = (TextView)findViewById(R.id.equipInven_TextView);
        loss_TextView = (TextView)findViewById(R.id.loss_TextView);
        gain_TextView = (TextView)findViewById(R.id.gain_TextView);


        new HiveListAsync().execute(MainActivity.currUser, "1", "1", "1", "1", "1", "1", "1", "1");

        // TODO set each TextView to display current hive information
        //username_TextView.setText("");

        deleteHive_Button.setOnClickListener(this);
        editHive_Button.setOnClickListener(this);
        createHive_Button.setOnClickListener(this);
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
                // TODO EDIT THE HIVE
                break;

            case R.id.createHive_Button:
                // TODO DON'T FORGET TO UPDATE THE HIVE
                new CreateHiveAsync().execute(MainActivity.currUser, stringApiaryName,
                                                 hive_TextView.getText().toString(),
                                                 inspection_TextView.getText().toString(),
                                                 health_TextView.getText().toString(),
                                                 honey_TextView.getText().toString(),
                                                 queenProduction_TextView.getText().toString(),
                                                 equipHive_TextView.getText().toString(),
                                                 equipInven_TextView.getText().toString(),
                                                 loss_TextView.getText().toString(),
                                                 gain_TextView.getText().toString());
                break;

        }
        // After button click, send to Hive page
        Intent intent2HiveSettings = new Intent(HiveSettings.this, Hive.class);
        startActivity(intent2HiveSettings);
    }
}
