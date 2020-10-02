package com.example.hivemanager;

import android.os.AsyncTask;

import com.mysql.jdbc.Blob;

public class GetPreferenceAsync extends AsyncTask<Object,Void,Void> {

    @Override
    protected Void doInBackground(Object... objects) {
        SQLConnection connect = new SQLConnection();
        String username = (String) objects[0];
        connect.getPreference(username);
        connect.closeConnection();
        return null;
    }
}