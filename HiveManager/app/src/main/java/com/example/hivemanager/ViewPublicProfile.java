package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewPublicProfile extends AppCompatActivity {
    ImageView imageView;
    TextView number,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_public_profile);
        imageView = (ImageView) findViewById(R.id.IDProf);
        imageView.setImageResource(R.drawable.bee1);
        number = (TextView) findViewById(R.id.Infonumber);
        email = (TextView)  findViewById(R.id.Infoemail);


        new DisplayProfileAsync().execute(MainActivity.currUser);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
        while(true){
            if(DisplayProfileAsync.rs != null){
                break;
            }
        }
        try {
            while (DisplayProfileAsync.rs.next()) {
                 email.setText(DisplayProfileAsync.rs.getString("email"));
                 number.setText(DisplayProfileAsync.rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            }
        },1000);
        //DisplayProfileAsync.connect.closeConnection();


        new GetPreferenceAsync().execute(MainActivity.currUser);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
        while(true){
            if(GetPreferenceAsync.rs != null){
                break;
            }
        }
        try {
            ResultSetMetaData rsmd = GetPreferenceAsync.rs.getMetaData();
            int col = rsmd.getColumnCount();
            while (GetPreferenceAsync.rs.next()) {
               for(int i = 3 ; i <= col ; i++) {
                   System.out.print(GetPreferenceAsync.rs.getString(i) + ", ");
               }
               System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            }
        },1000);
        //GetPreferenceAsync.connect.closeConnection();
        //GetPreferenceAsync.rs // is [0,1,2,3,4,5],
                                 // [0,1,2,3,4,5]





    }
}