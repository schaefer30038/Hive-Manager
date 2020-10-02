package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplaySpecificHiveAsync extends AsyncTask<String, Void, Void> {
    static SQLConnection connect;
    static ResultSet resultSet;
    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        String hive = strings[2];
        resultSet = connect.displaySpecificHive(username, apiary, hive);
        MainActivity.done = true;
        return null;
    }
}
