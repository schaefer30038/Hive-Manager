package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNewAccount extends AppCompatActivity {

    Button upload_Button;
    EditText newAccountUsername_PlainText, newAccountPassword_PlainText,
            newAccountAddress_PlainText, newAccountPhone_PlainText,newAccountEmail_PlainText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        newAccountUsername_PlainText = (EditText) findViewById(R.id.newAccountUsername_PlainText);
        newAccountPassword_PlainText = (EditText) findViewById(R.id.newAccountPassword_PlainText);
        newAccountAddress_PlainText = (EditText) findViewById(R.id.newAccountAddress_PlainText);
        newAccountPhone_PlainText = (EditText) findViewById(R.id.newAccountPhone_PlainText);
        newAccountEmail_PlainText = (EditText) findViewById(R.id.newAccountEmail_PlainText);

        upload_Button = (Button) findViewById(R.id.upload_Button);
        upload_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    openGallery();
            }
        });
    }
}