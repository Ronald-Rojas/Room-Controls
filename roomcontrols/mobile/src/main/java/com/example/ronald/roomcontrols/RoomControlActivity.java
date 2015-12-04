package com.example.ronald.roomcontrols;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

/**
 * Created by ronald on 11/15/15.
 */
public class RoomControlActivity extends Activity {
    public static final String PREFS = "PREFS";
    public static final String PREFS_Light1 = "light1Boolean";
    public static final String PREFS_Light2 = "light2Boolean";
    public static final String PREFS_Light3 = "light3Boolean";
    public static final String PREFS_Light4 = "light4Boolean";
    public static final String PREFS_AC = "ACBoolean";

    private Switch ACSwitch;
    private Switch light1Switch;
    private Switch light2Switch;
    private Switch light3Switch;
    private Switch light4Switch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controlroom);
        SharedPreferences settings = getSharedPreferences(PREFS, MODE_PRIVATE);

        ACSwitch = (Switch) findViewById(R.id.ACswitch);
        light1Switch = (Switch) findViewById(R.id.light1switch);
        light2Switch = (Switch) findViewById(R.id.light2switch);
        light3Switch = (Switch) findViewById(R.id.light3switch);
        light4Switch = (Switch) findViewById(R.id.light4switch);
    }

    public void CommitControl(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
