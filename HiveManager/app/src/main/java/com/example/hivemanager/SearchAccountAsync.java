package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.Blob;

public class SearchAccountAsync extends AsyncTask<String,Void,Void> {
    static SQLConnection connect;

    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        String password = strings[1];
        MainActivity.login = connect.searchAccount(username, password);
        MainActivity.done = true;
        return null;
    }
}
