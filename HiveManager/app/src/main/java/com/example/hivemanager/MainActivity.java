package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button Login_Button, CreateNewAccount_Button, Upload_Button;
    EditText LogicUsername_PlainText,LoginPassword_PlainText, NewAccountUsername_PlainText, NewAccountPassword_PlainText,
             NewAccountAddress_PlainText, NewAccountPhone_PlainText, NewAccountEmail_PlainText;
    ImageView uploadPicture_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Button = (Button) findViewById(R.id.Login_Button);
        CreateNewAccount_Button = (Button) findViewById(R.id.CreateNewAccount_Button);
        Upload_Button = (Button) findViewById(R.id.Upload_Button);


        LogicUsername_PlainText = (EditText) findViewById(R.id.LogicUsername_PlainText);
        LoginPassword_PlainText = (EditText) findViewById(R.id.LoginPassword_PlainText);
        NewAccountUsername_PlainText = (EditText) findViewById(R.id.NewAccountUsername_PlainText);
        NewAccountPassword_PlainText = (EditText) findViewById(R.id.NewAccountPassword_PlainText);
        NewAccountAddress_PlainText = (EditText) findViewById(R.id.NewAccountAddress_PlainText);
        NewAccountPhone_PlainText = (EditText) findViewById(R.id.NewAccountPhone_PlainText);
        NewAccountEmail_PlainText = (EditText) findViewById(R.id.NewAccountEmail_PlainText);

        uploadPicture_ImageView = (ImageView) findViewById(R.id.IDProf);

        Login_Button.setOnClickListener(this);
        CreateNewAccount_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Login_Button:
                Intent intent = newIntent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.CreateNewAccount_Button:
                // INSERT ACTION HERE
                break;
        }
    }
}