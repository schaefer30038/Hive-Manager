package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Blob;
import java.sql.SQLException;

public class CreatePublicProfile extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;

    private static String rslt = "0";
    private static String hlth = "0";
    private static String hny = "0";
    private static String qn = "0";
    private static String hve = "0";
    private static String invent = "0";
    private static String lss =  "0";
    private static String gn = "0";
    Uri imageUri;
    ImageView imageView;
    Button Upload_Button,setPref;
    CheckBox result,health,honey,queen,hiveequip,inventequip,losses,gains;
    EditText PublicProfilePhone_PlainText, PublicProfileEmail_PlainText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_public_profile);


        PublicProfilePhone_PlainText = (EditText) findViewById(R.id.newAccountPhone_PlainText);
        PublicProfileEmail_PlainText = (EditText) findViewById(R.id.newAccountEmail_PlainText);
        rslt = "0";
        hlth = "0";
        hny = "0";
        qn = "0";
        hve = "0";
        invent ="0";
        lss =  "0";
        gn = "0";
        new DisplayProfileAsync().execute(MainActivity.currUser);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
        while(true){
            if(MainActivity.done == true){
                MainActivity.done = false;
                break;
            }
        }
        try {
            while (DisplayProfileAsync.rs.next()) {
                PublicProfileEmail_PlainText.setText(DisplayProfileAsync.rs.getString("email"));
                PublicProfilePhone_PlainText.setText(DisplayProfileAsync.rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    }
        },1000);



        //DisplayProfileAsync.connect.closeConnection();


        setPref = (Button) findViewById(R.id.setHivepref_Button);
        imageView = (ImageView) findViewById(R.id.IDProf);
        imageView.setImageResource(R.drawable.bee1);
        Upload_Button = (Button) findViewById(R.id.upload_Button);


        setPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });


        Upload_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        result = findViewById(R.id.result);
        health = findViewById(R.id.health);
        queen = findViewById(R.id.queen);
        honey = findViewById(R.id.honey);
        hiveequip = findViewById(R.id.equipmentHive);
        inventequip = findViewById(R.id.equipmentInvent);
        losses = findViewById(R.id.loss);
        gains = findViewById(R.id.gain);



        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.isChecked()){
                    rslt = "1";
                }
                else rslt = "0";
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(health.isChecked()){
                    hlth = "1";
                }
                else hlth = "0";
            }
        });
        queen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queen.isChecked()){
                    qn = "1";
                }
                else qn = "0";
            }
        });
        honey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(honey.isChecked()){
                    hny = "1";
                }
                else hny = "0";
            }
        });
        hiveequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hiveequip.isChecked()){
                    hve = "1";
                }
                else hve = "0";
            }
        });
        inventequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inventequip.isChecked()){
                    invent = "1";
                }
                else invent = "0";
            }
        });
        losses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(losses.isChecked()){
                    //System.out.println("FUck me in the ass");
                    lss = "1";
                }
                else lss = "0";
            }
        });
        gains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gains.isChecked()){
                    gn = "1";
                }
                else gn = "0";
            }
        });

    }




    private void sendData() {
        //Somehow store one photo and send it
        new UpdateProfileAsync().execute(MainActivity.currUser, null, PublicProfileEmail_PlainText.getText().toString(),PublicProfilePhone_PlainText.getText().toString(),rslt,hlth,hny,qn,hve,invent,lss,gn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (MainActivity.done == true) {
                        MainActivity.done = false;
                        break;
                    }
                }
                UpdateProfileAsync.connect.closeConnection();
            }
        },1000);
        Intent intent2CreatePublicProfile = new Intent(CreatePublicProfile.this, MainActivity2.class);
        startActivity(intent2CreatePublicProfile);

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}