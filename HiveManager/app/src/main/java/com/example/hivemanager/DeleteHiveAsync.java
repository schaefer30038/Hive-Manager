package com.example.hivemanager;

import android.os.AsyncTask;

public class DeleteHiveAsync extends AsyncTask<String,Void,Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        String apiary = strings[1];
        String hive = strings[2];
        MainActivity.connection.deleteHive(username, apiary, hive);
        done = true;
        return null;
    }
}