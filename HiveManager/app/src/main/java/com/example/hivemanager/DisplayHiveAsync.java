package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplayHiveAsync extends AsyncTask<String, Void, ResultSet> {

    @Override
    protected ResultSet doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        ResultSet rs = connect.displayHive(username);
        connect.closeConnection();
        return rs;
    }

    @Override
    protected void onPostExecute(ResultSet resultSet) {
        super.onPostExecute(resultSet);
    }
}