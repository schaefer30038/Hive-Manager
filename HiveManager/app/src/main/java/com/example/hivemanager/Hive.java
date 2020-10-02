package com.example.hivemanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hive extends AppCompatActivity implements View.OnClickListener {
    Button addHive_Button;
    ListView listView;
    public static final String EXTRA_TEXT = "com.example.application.Hive.EXTRA_TEXT";
    static ResultSet resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);

        listView = (ListView)findViewById(R.id.listview);
        addHive_Button = (Button)findViewById(R.id.addHive_Button);

        addHive_Button.setOnClickListener(this);


        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<HiveObject> hiveList = new ArrayList<>();
        new DisplayHiveAsync().execute(MainActivity.currUser);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
            }
        },1000);

        // Create a loop, adding all hive entries into this list. This
        // will add them all in a list.
        arrayList.add("testing");
        arrayList.add("this");
        arrayList.add("thing");
        arrayList.add("because");
        arrayList.add("it");
        arrayList.add("won't");
        arrayList.add("freaking");
        arrayList.add("work");
        arrayList.add("some");
        arrayList.add("of");
        arrayList.add("the");
        arrayList.add("times");
        arrayList.add("so");
        arrayList.add("please");
        arrayList.add("work");
        arrayList.add("this");
        arrayList.add("time");
        arrayList.add("por");
        arrayList.add("favor");

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
                    hiveList.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Hive.this, "clicked item: "+position+" "+arrayList.get(position).toString(), Toast.LENGTH_SHORT).show(); // Solely to demonstrate it works.
                Intent intent2hiveSettings = new Intent(Hive.this, HiveSettings.class);
                intent2hiveSettings.putExtra(EXTRA_TEXT, arrayList.get(position).toString());   // Stores the name of the hive
                startActivity(intent2hiveSettings);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addHive_Button:
                Intent intent2HiveSettings = new Intent(Hive.this, HiveSettings.class);
                intent2HiveSettings.putExtra(EXTRA_TEXT, "NULL");   // If creating a page, SENDS NULL
                startActivity(intent2HiveSettings);
                break;
        }
    }

}