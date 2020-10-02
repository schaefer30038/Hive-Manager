package com.example.hivemanager;

import android.os.AsyncTask;

public class UpdateHiveAsync extends AsyncTask<String,Void,Void> {

    static boolean done = false;

    @Override
    protected Void doInBackground(String... strings) {
        String username = strings[0];
        String apiary = strings[1];
        String oldhive = strings[2];
        String newhive = strings[3];
        String inspection = strings[4];
        String health = strings[5];
        String honey = strings[6];
        String queenproduction = strings[7];
        String equiphive = strings[8];
        String equipinven = strings[9];
        int loss = Integer.parseInt(strings[10]);
        int gain = Integer.parseInt(strings[11]);
        MainActivity.connection.updateHive(username, apiary, oldhive, newhive, inspection, health, honey, queenproduction, equiphive, equipinven,
                loss, gain);
        done = true;
        return null;
    }
}