package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

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

        deleteHive_Button = (Button)findViewById(R.id.deleteHive_Button);
        editHive_Button = (Button)findViewById(R.id.editHive_Button);
        createHive_Button = (Button)findViewById(R.id.createHive_Button);

        hiveInfo_TextView = (TextView)findViewById(R.id.hiveInfo_TextView);



        // These are edit-able by the user
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

        new HiveListAsync().execute(MainActivity.currUser, "1", "1", "1", "1", "1", "1", "1", "1");

        while(true) {
            if (HiveListAsync.rs != null)
                break;
            }


        String inspection = "";
        String health = "";
        String honey = "";
        String queenproduction = "";
        String equiphive = "";
        String equipinven = "";
        int loss = 0;
        int gain = 0;

        if(Hive.SELECT_BUTTON) {
            System.out.println("SECOND CHECK");



            new DisplaySpecificHiveAsync().execute(MainActivity.currUser, Hive.apiaryName, stringHiveName);

            while(true) {
                if (DisplaySpecificHiveAsync.resultSet != null) {
                    break;
                }
            }
            ResultSet resultSet = DisplaySpecificHiveAsync.resultSet;
            System.out.println(MainActivity.currUser + " : " + Hive.apiaryName + " : " + stringHiveName);
            if (resultSet != null)
            try {
                    while (resultSet.next()) {
                        inspection = resultSet.getString("inspection");
                        health = resultSet.getString("health");
                        honey = resultSet.getString("honey");
                        queenproduction = resultSet.getString("queenproduction");
                        equiphive = resultSet.getString("equiphive");
                        equipinven = resultSet.getString("equipinven");
                        loss = resultSet.getInt("loss");
                        gain = resultSet.getInt("gain");
                    }

                    } catch (SQLException e ) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

            //DisplaySpecificHiveAsync.connect.closeConnection();
        }

        // Populate entries if selected a hive, otherwise leave empty (creating new hive)
        if(Hive.SELECT_BUTTON) {
            //apiary_TextView.setEnabled(false);
            apiary_TextView.setText(Hive.apiaryName);
            hive_TextView.setText(stringHiveName);
            inspection_TextView.setText(inspection);
            health_TextView.setText(health);
            honey_TextView.setText(honey);
            queenProduction_TextView.setText(queenproduction);
            equipHive_TextView.setText(equiphive);
            equipInven_TextView.setText(equipinven);
            loss_TextView.setText("" + loss);
            gain_TextView.setText("" + gain);
        }
        else{
            //apiary_TextView.setEnabled(true);
            apiary_TextView.setText("");
            hive_TextView.setText("");
            inspection_TextView.setText("");
            health_TextView.setText("");
            honey_TextView.setText("");
            queenProduction_TextView.setText("");
            equipHive_TextView.setText("");
            equipInven_TextView.setText("");
            loss_TextView.setText("");
            gain_TextView.setText("");
        }


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
                new DeleteHiveAsync().execute(MainActivity.currUser, Hive.apiaryName, stringHiveName);
                while(true){
                    if(MainActivity.done == true){
                        MainActivity.done = false;
                        break;
                    }
                }

                break;

            case R.id.editHive_Button:
                new UpdateHiveAsync().execute(MainActivity.currUser,
                                              Hive.apiaryName,
                                              stringHiveName,
                                              hive_TextView.getText().toString(),
                                              inspection_TextView.getText().toString(),
                                              health_TextView.getText().toString(),
                                              honey_TextView.getText().toString(),
                                              queenProduction_TextView.getText().toString(),
                                              equipHive_TextView.getText().toString(),
                                              equipInven_TextView.getText().toString(),
                                              loss_TextView.getText().toString(),
                                              gain_TextView.getText().toString());

                while(true){
                    if(MainActivity.done == true){
                        MainActivity.done = false;
                        break;
                    }
                }
                break;

            case R.id.createHive_Button:
                new CreateHiveAsync().execute(MainActivity.currUser,
                                                 apiary_TextView.getText().toString(),
                                                 hive_TextView.getText().toString(),
                                                 inspection_TextView.getText().toString(),
                                                 health_TextView.getText().toString(),
                                                 honey_TextView.getText().toString(),
                                                 queenProduction_TextView.getText().toString(),
                                                 equipHive_TextView.getText().toString(),
                                                 equipInven_TextView.getText().toString(),
                                                 loss_TextView.getText().toString(),
                                                 gain_TextView.getText().toString());

                while(true){
                    if(MainActivity.done == true){
                        MainActivity.done = false;
                        break;
                    }
                }


                break;

        }

        // After button click, send to Hive page
        Intent intent2HiveSettings = new Intent(HiveSettings.this, MainActivity2.class);
        startActivity(intent2HiveSettings);
    }
}
