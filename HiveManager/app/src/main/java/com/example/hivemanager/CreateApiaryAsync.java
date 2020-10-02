package com.example.hivemanager;

import android.os.AsyncTask;

public class CreateApiaryAsync extends AsyncTask<String,Void,Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        String apiary = strings[1];
        MainActivity.connection.createApiary(username, apiary);
        done = true;
        return null;
    }
}