package com.example.hivemanager;

import android.os.AsyncTask;

import com.mysql.jdbc.Blob;

import java.sql.ResultSet;

public class GetPreferenceAsync extends AsyncTask<Object,Void,Void> {
    static SQLConnection connect;
    static ResultSet rs;
    @Override
    protected Void doInBackground(Object... objects) {
        connect = new SQLConnection();
        String username = (String) objects[0];
        rs = connect.getPreference(username);
        MainActivity.done = true;
        return null;
    }
}