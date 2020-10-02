package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApiarySettings extends AppCompatActivity implements View.OnClickListener{
    TextView nameOfApiary_TextView;
    Button createApiary_Button, delete_Apiary_Button, updateApiary_Button;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary_settings);

        listview = (ListView) findViewById(R.id.listview);

        nameOfApiary_TextView = (TextView)findViewById(R.id.nameOfApiary_TextView);
        nameOfApiary_TextView.setText(Apiary.getApiaryName);

        createApiary_Button = (Button)findViewById(R.id.createApiary_Button);
        delete_Apiary_Button = (Button)findViewById(R.id.delete_Apiary_Button);
        updateApiary_Button = (Button)findViewById(R.id.updateApiary_Button);

        createApiary_Button.setOnClickListener(this);
        delete_Apiary_Button.setOnClickListener(this);
        updateApiary_Button.setOnClickListener(this);


        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<HiveObject> hiveList = new ArrayList<>();

        if(Apiary.SELECT_APIARY) {
            new DisplayHiveAsync().execute(MainActivity.currUser);

            while (true) {
                if (DisplayHiveAsync.rs != null) {
                    break;
                }
            }
            ResultSet resultSet = DisplayHiveAsync.rs;
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        HiveObject temp = new HiveObject();
                        String apiary = resultSet.getString("apiary");
                        String hive = resultSet.getString("hive");
                        String inspection = resultSet.getString("inspection");
                        String health = resultSet.getString("health");
                        String honey = resultSet.getString("honey");
                        String queenproduction = resultSet.getString("queenproduction");
                        String equiphive = resultSet.getString("equiphive");
                        String equipinven = resultSet.getString("equipinven");
                        int loss = resultSet.getInt("loss");
                        int gain = resultSet.getInt("gain");
                        temp.setApiary(apiary);
                        temp.setHive(hive);
                        temp.setInspection(inspection);
                        temp.setHealth(health);
                        temp.setHoney(honey);
                        temp.setQueenProduction(queenproduction);
                        temp.setEquipHive(equiphive);
                        temp.setEquipInven(equipinven);
                        temp.setLoss(loss);
                        temp.setGain(gain);
                        if(apiary.equals(Apiary.getApiaryName))
                            hiveList.add(temp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DisplayHiveAsync.connect.closeConnection();
            //arrayList.add("testing");
            for(int i = 0; i < hiveList.size(); i++) {
                arrayList.add( hiveList.get(i).getHive().toString() );
            }
        }
        else{
            // Do Nothing
            while(!arrayList.isEmpty())
                arrayList.remove(0);
        }




        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listview.setAdapter(arrayAdapter);




    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.createApiary_Button:
                new CreateApiaryAsync().execute(MainActivity.currUser, nameOfApiary_TextView.getText().toString());

                while(true) {
                    if (MainActivity.done) {
                        MainActivity.done = false;
                        break;
                    }
                }

                break;

            case R.id.delete_Apiary_Button:
                new DeleteApiaryAsync().execute(MainActivity.currUser, Apiary.getApiaryName);

                while(true) {
                    if (MainActivity.done) {
                        MainActivity.done = false;
                        break;
                    }
                }

                break;

            case R.id.updateApiary_Button:
                new UpdateApiaryAsync().execute(MainActivity.currUser, Apiary.getApiaryName, nameOfApiary_TextView.getText().toString());
                while(true) {
                    if (MainActivity.done) {
                        MainActivity.done = false;
                        break;
                    }
                }

                break;

        }
        Intent intent2mainPage2 = new Intent(ApiarySettings.this, MainActivity2.class);
        startActivity(intent2mainPage2);
    }
}
