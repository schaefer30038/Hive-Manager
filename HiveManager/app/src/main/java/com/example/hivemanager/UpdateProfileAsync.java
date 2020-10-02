package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.Blob;

public class UpdateProfileAsync extends AsyncTask<Object,Void,Void> {
    static SQLConnection connect;
    @Override
    protected Void doInBackground(Object... objects) {
        connect = new SQLConnection();
        String username = (String) objects[0];
        Blob picture = (Blob) objects[1];
        String email = (String) objects[2];
        String phone = (String) objects[3];
        connect.updateProfile(username, picture, email, phone);
        MainActivity.done = true;
        return null;
    }
}
