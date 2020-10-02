package com.example.hivemanager;

import android.os.AsyncTask;

public class DeleteHiveAsync extends AsyncTask<String,Void,Void> {
    static SQLConnection connect;
    @Override
    protected Void doInBackground(String... strings) {
        connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        String hive = strings[2];
        connect.deleteHive(username, apiary, hive);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.done = true;
    }
}