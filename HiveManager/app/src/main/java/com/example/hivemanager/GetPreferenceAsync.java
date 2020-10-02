package com.example.hivemanager;

import android.os.AsyncTask;

import com.mysql.jdbc.Blob;

public class GetPreferenceAsync extends AsyncTask<Object,Void,Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(Object... objects) {
        String username = (String) objects[0];
        MainActivity.connection.getPreference(username);
        done = true;
        return null;
    }
}