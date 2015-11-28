package com.example.ronald.roomcontrols;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by ronald on 9/15/15.
 */
public class SettingsMenu extends AppCompatActivity{

    private BluetoothAdapter myBluetooth = null;
    private Set pairedDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null){
            Toast.makeText(getApplicationContext(), "Bluetooth Devide not available", Toast.LENGTH_LONG);
            finish();
        }
        else {
            if(myBluetooth.isEnabled()){}
            else{
                Intent turnToothOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnToothOn, 1);
            }
        }
    }

    public void submitSettings(View view) {
        //TODO create submit settings for the application. Make bluetooth code



        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
