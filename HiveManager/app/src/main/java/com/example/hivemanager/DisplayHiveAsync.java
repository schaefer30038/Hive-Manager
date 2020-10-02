package com.example.hivemanager;

import android.os.AsyncTask;

public class DisplayHiveAsync extends AsyncTask<String, Void, Void> {

    static boolean done = false;
    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        Hive.resultSet = MainActivity.connection.displayHive(username);
        done = true;
        return null;
    }
}