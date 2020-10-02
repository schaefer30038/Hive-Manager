package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplayProfileAsync extends AsyncTask<String, Void, Void> {

    static ResultSet rs;
    static boolean done = false;
    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        rs = MainActivity.connection.displayProfile(username);
        done = true;
        return null;
    }


}