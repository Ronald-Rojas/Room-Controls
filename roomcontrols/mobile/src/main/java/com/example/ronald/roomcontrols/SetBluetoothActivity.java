package com.example.ronald.roomcontrols;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ronald on 12/5/15.
 */
public class SetBluetoothActivity extends Activity{
    public static final String PREFS = "PREFS";
    SharedPreferences settings = getSharedPreferences(PREFS, MODE_PRIVATE);
    public static final String BLUESTR = "BlueToothStr";
    public static final int REQUEST_ENABLE_BT = 100;
    ArrayList<String> BTArray;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setbluetooth);
        //make sure that the device has bluetooth
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            // device does not support bluetooth
        }

        // enable the bluetooth device if it is not enabled
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_ENABLE_BT);
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0 ){
            for(BluetoothDevice device : pairedDevices){
                BTArray.add(device.getName() + "\n" + device.getAddress());
            }
        }


    }
}
