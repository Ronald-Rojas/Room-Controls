package com.example.ronald.roomcontrols;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

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
    boolean light1Bool;
    boolean light2Bool;
    boolean light3Bool;
    boolean light4Bool;
    boolean ACBool;


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
        ACSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    ACBool = true;
                    } else {
                    ACBool = false;
                }

            }
        });
        light1Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    light1Bool = true;
                }else{
                    light1Bool = false;
                }

            }
        });
        light2Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    light2Bool = true;
                    }else{
                    light2Bool = false;
                }

            }
        });
        light3Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    light3Bool = true;
                }else{
                    light3Bool = false;
                }

            }
        });
        light4Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    light4Bool = true;
                } else {
                    light4Bool = false;
                }

            }
        });
    }

    public void CommitControl(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean(PREFS_AC, ACBool);
        editor.putBoolean(PREFS_Light1, light1Bool);
        editor.putBoolean(PREFS_Light2, light2Bool);
        editor.putBoolean(PREFS_Light3, light3Bool);
        editor.putBoolean(PREFS_Light4, light4Bool);
        editor.apply();

        Intent bluetooth = new Intent(this, SetBluetoothActivity.class);
        startActivity(bluetooth);




//        if(didItWork){
//            Toast.makeText(getApplicationContext(), "It Worked!", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(getApplicationContext()," It Failed", Toast.LENGTH_LONG).show();
//        }





        startActivity(new Intent(this, HomeActivity.class));

    }
}
