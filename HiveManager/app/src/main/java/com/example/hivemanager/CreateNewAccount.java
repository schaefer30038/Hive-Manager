package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateNewAccount extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView imageView;
    Button Upload_Button;
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
        imageView = (ImageView) findViewById(R.id.IDProf);
        imageView.setImageResource(R.drawable.bee1);
        Upload_Button = (Button) findViewById(R.id.upload_Button);
        Upload_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
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