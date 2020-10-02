package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplayApiaryAsync extends AsyncTask<String, Void, Void> {

    static ResultSet rs;
    static boolean done = false;
    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        rs = MainActivity.connection.displayApiary(username);
        done = true;
        return null;
    }

}