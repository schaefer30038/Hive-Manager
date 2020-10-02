package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ApiarySettings extends AppCompatActivity implements View.OnClickListener{
    TextView nameOfApiary_TextView;
    Button createApiary_Button, delete_Apiary_Button, updateApiary_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary_settings);

        nameOfApiary_TextView = (TextView)findViewById(R.id.nameOfApiary_TextView);
        nameOfApiary_TextView.setText(Apiary.getApiaryName);

        createApiary_Button = (Button)findViewById(R.id.createApiary_Button);
        delete_Apiary_Button = (Button)findViewById(R.id.delete_Apiary_Button);
        updateApiary_Button = (Button)findViewById(R.id.updateApiary_Button);

        createApiary_Button.setOnClickListener(this);
        delete_Apiary_Button.setOnClickListener(this);
        updateApiary_Button.setOnClickListener(this);




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