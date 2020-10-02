package com.example.hivemanager;

import android.os.AsyncTask;

public class UpdateApiaryAsync extends AsyncTask<String,Void,Void> {
    static SQLConnection connect;

    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        String oldpiary = strings[1];
        String newpiary = strings[2];
        connect.updateApiary(username, oldpiary, newpiary);
        MainActivity.done = true;
        return null;
    }
}