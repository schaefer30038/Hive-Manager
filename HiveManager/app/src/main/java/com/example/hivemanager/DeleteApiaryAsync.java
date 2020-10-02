package com.example.hivemanager;

import android.os.AsyncTask;

public class DeleteApiaryAsync extends AsyncTask<String,Void,Void> {
    static SQLConnection connect;
    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        connect.deleteApiary(username, apiary);
        MainActivity.done = true;
        return null;
    }
}