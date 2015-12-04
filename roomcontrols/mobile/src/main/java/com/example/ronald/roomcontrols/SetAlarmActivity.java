package com.example.ronald.roomcontrols;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by ronald on 11/15/15.
 */
public class SetAlarmActivity extends Activity{
    public static final String PREFS = "PREFS";
    public static final String PREFS_KEY = "alarmBoolean";
    private Switch alarmSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);
        alarmSwitch = (Switch) findViewById(R.id.alarmswitch);
        alarmSwitch.setChecked(true);
        SharedPreferences  settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    SharedPreferences settings;
                    SharedPreferences.Editor editor;
                    settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE); //1
                    editor = settings.edit(); //2

                    editor.putBoolean(PREFS_KEY, true); //3
                    editor.commit(); //4
                }else{
                    SharedPreferences settings;
                    SharedPreferences.Editor editor;
                    settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE); //1
                    editor = settings.edit(); //2

                    editor.putBoolean(PREFS_KEY, false); //3
                    editor.commit(); //4

                }

            }
        });
    }

    public void CommitAlarm(View view) {
        startActivity(new Intent (getApplicationContext(), HomeActivity.class));

    }
}
