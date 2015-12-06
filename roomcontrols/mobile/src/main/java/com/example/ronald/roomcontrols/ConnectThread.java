package com.example.ronald.roomcontrols;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * Created by ronald on 12/5/15.
 */
public class ConnectThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    public ConnectThread(BluetoothDevice device){
        BluetoothSocket temp = null;
        mmDevice = device;

        try{
            temp = device.createRfcommSocketToServiceRecord(mmDevice.getUuids()[0].getUuid());
        }
        catch (IOException e ){}
        mmSocket = temp;
    }
    public void run(){
        //TODO: decide on whether i need to use the discovery option
     //   mBluetoothAdapter.cancelDiscovery();

        try{
            //Connect through the socket
            mmSocket.connect();
        }
        catch (IOException e ){
            try{
                mmSocket.close();
            }catch (IOException ex ){}
            return;
        }

        sendDataThread sendData = new sendDataThread(mmSocket);

        //TODO: set up writing data array
        //sendData.write();
    }
    public void cancel(){
        try{
            mmSocket.close();
        }catch (IOException e ){}
    }
}
