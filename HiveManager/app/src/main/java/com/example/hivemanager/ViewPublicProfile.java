package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewPublicProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView imageView;
    Button back;
    TextView number,email,info,user;
    ArrayList<String>[] list = null;
    //static ArrayList<String>[] list = new ArrayList<String>[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_public_profile);
        imageView = (ImageView) findViewById(R.id.IDProf);
        imageView.setImageResource(R.drawable.bee1);
        number = (TextView) findViewById(R.id.Infonumber);
        email = (TextView)  findViewById(R.id.Infoemail);
        user = (TextView)findViewById(R.id.user);
        user.setText(MainActivity.currUser);
        user.setEnabled(false);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });
        Spinner dropdown = findViewById(R.id.hivelist);
         info = findViewById(R.id.info);


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
        },2000);
//        DisplayProfileAsync.connect.closeConnection();

        ArrayList<String> names = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        new GetPreferenceAsync().execute(MainActivity.currUser);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (GetPreferenceAsync.rs != null) {
                        break;
                    }
                }
                    list = SQLConnection.hiveSelectedInfo(GetPreferenceAsync.rs);

                for (int j = 1; j < list[0].size(); j++) {
                    adapter.add(list[2].get(j));
                }
                //Print data

            }
        },2000);

    }

    private void undo() {
        Intent intent2CreatePublicProfile = new Intent(ViewPublicProfile.this, MainActivity2.class);
        startActivity(intent2CreatePublicProfile);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                String print = "";
                String[] tp = {"Inspection Result: ", "Health: ", "Honey: ","Queen Production: ", "Hive Equipment: ","Inventory Equipment: ","Losses: ","Gains: "};
                for (int i = 3; i < list.length; i++) {
                    switch(list[i].get(0))
                    {
                        case "inspection":
                            print = print + tp[0];
                            break;
                        case "health":
                            print = print + tp[1];
                            break;
                        case "honey":
                            print = print + tp[2];
                            break;
                        case "queenproduction":
                            print = print + tp[3];
                            break;
                        case "equiphive":
                            print = print + tp[4];
                            break;
                        case "equipinven":
                            print = print + tp[5];
                            break;
                        case "loss":
                            print = print + tp[6];
                            break;
                        case "gain":
                            print = print + tp[7];
                            break;
                    }
                    //print = (print + list[i].get(0) + ": ");

                    print = print + list[i].get(position+1) + "\n";
                }
        info.setText(print);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}