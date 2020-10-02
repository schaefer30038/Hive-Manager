package com.example.hivemanager;

import android.os.AsyncTask;

public class DisplayHiveAsync extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        Hive.resultSet = connect.displayHive(username);
        connect.closeConnection();
        return null;
    }
}