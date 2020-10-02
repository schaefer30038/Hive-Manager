package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class EditPreferenceAsync  extends AsyncTask<String, Void, Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        Boolean inspection = 0 != Integer.parseInt(strings[1]);
        Boolean health = 0 != Integer.parseInt(strings[2]);
        Boolean honey = 0 != Integer.parseInt(strings[3]);
        Boolean queenproduction = 0 != Integer.parseInt(strings[4]);
        Boolean equiphive = 0 != Integer.parseInt(strings[5]);
        Boolean equipinven = 0 != Integer.parseInt(strings[6]);
        Boolean loss = 0 != Integer.parseInt(strings[7]);
        Boolean gain = 0 != Integer.parseInt(strings[8]);
        MainActivity.connection.editPreference(username, inspection, health, honey, queenproduction, equiphive, equipinven,
                loss, gain);
        done = true;
        return null;
    }
}
