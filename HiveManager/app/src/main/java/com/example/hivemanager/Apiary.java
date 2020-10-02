package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Apiary extends AppCompatActivity {
    TextView apiaryInfo_TextView;
    Button addApiary_Button;
    ListView listView;

    static String getApiaryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        listView = (ListView)findViewById(R.id.listview);

        apiaryInfo_TextView = (TextView) findViewById(R.id.ManageApiaries_Button);

        addApiary_Button = (Button) findViewById(R.id.addApiary_Button);

        final ArrayList<String> arrayList = new ArrayList<>();

        new DisplayApiaryAsync().execute(MainActivity.currUser);

        while(true) {
            if (DisplayApiaryAsync.rs != null) {
                break;
            }
        }

        try {
            ResultSet rs = DisplayApiaryAsync.rs;
            if (rs != null) {
                while (rs.next()) {
                    String apiary = rs.getString("apiary");
                    arrayList.add(apiary);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Apiary.this, "clicked item: "+position+" "+arrayList.get(position).toString(), Toast.LENGTH_SHORT).show(); // Solely to demonstrate it works.
                Intent intent2apiarySettings = new Intent(Apiary.this, ApiarySettings.class);
                getApiaryName = arrayList.get(position).toString();
                startActivity(intent2apiarySettings);
            }
        });







    }
}