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
        boolean inspection = 0!=Integer.parseInt((String) objects[4]);
        boolean health = 0!=Integer.parseInt((String) objects[5]);
        boolean honey = 0!=Integer.parseInt((String) objects[6]);
        boolean queen = 0!=Integer.parseInt((String) objects[7]);
        boolean hiveequip = 0!=Integer.parseInt((String) objects[8]);
        boolean invent = 0!=Integer.parseInt((String) objects[9]);
        boolean loss = 0!=Integer.parseInt((String) objects[10]);
        boolean gain = 0!=Integer.parseInt((String) objects[11]);


        connect.updateProfile(username, picture, email, phone);
        connect.editPreference(username,inspection,health,honey,queen,hiveequip,invent,loss,gain);
        MainActivity.done = true;
        return null;
    }
}
