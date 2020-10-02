package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplayProfileAsync extends AsyncTask<String, Void, Void> {
    static SQLConnection connect;
    static ResultSet rs;
    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        rs =connect.displayProfile(username);
        MainActivity.done = true;
        return null;
    }


}