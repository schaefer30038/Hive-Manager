package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.Blob;

public class UpdateProfileAsync extends AsyncTask<Object,Void,Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(Object... objects) {
        String username = (String) objects[0];
        Blob picture = (Blob) objects[1];
        String email = (String) objects[2];
        String phone = (String) objects[3];
        MainActivity.connection.updateProfile(username, picture, email, phone);
        done = true;
        return null;
    }
}
