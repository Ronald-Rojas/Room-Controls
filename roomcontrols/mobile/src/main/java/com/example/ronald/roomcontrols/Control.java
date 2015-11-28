package com.example.ronald.roomcontrols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ronald on 9/15/15.
 */
public class Control extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
    }

    public void submitControl(View view) {
        //TODO: create submit control method and also the bluetooth submit method.
        //This method will also coincide with the cod the the submitSetting method in the SettingsMenu



        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
