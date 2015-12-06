package com.example.ronald.roomcontrols;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ronald on 12/5/15.
 */
public class sendDataThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final OutputStream mmOutStream;

    protected sendDataThread(BluetoothSocket socket){

        // We only need to send data to the arduino so there
        // is no need to have an output stream
        mmSocket = socket;
        OutputStream tmpOut = null;
        //get output stream
        try{
            tmpOut = socket.getOutputStream();

        }catch (IOException e ){}
        mmOutStream = tmpOut;

    }
    // this app will not be recieve data so there is no need for an input stream
    // this function will be called from the main activity
    public void write(byte[] bytes){
        try{
            mmOutStream.write(bytes);

        }catch (IOException e ){}
    }
}
