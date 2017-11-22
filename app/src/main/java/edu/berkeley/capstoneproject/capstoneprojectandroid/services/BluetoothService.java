package edu.berkeley.capstoneproject.capstoneprojectandroid.services;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import timber.log.Timber;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.activities.DeviceActivity.HANDLER_MSG_READ;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.activities.DeviceActivity.HANDLER_MSG_STATE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.activities.DeviceActivity.HANDLER_MSG_TOAST;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.activities.DeviceActivity.HANDLER_MSG_WRITE;

/**
 * Created by Alex on 18/10/2017.
 */

public class BluetoothService {

    private static final UUID MY_UUID = UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB");

    public static final String BLUETOOTH_SERVICE_TOAST = "Toast";


    public enum BluetoothServiceStatus {
        NONE,
        CONNECTING,
        CONNECTED
    }

    private BluetoothServiceStatus mState;

    private final Handler mHandler;
    private final BluetoothDevice mDevice;

    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;

    public BluetoothService(Handler handler, BluetoothDevice device) {
        mHandler = handler;
        mDevice = device;
        mState = BluetoothServiceStatus.NONE;
    }



    public synchronized BluetoothServiceStatus getState() {
        return mState;
    }

    private synchronized void setState(BluetoothServiceStatus state) {
        mState = state;
        notifyStateChange();
    }

    public synchronized void connect() {
        Timber.d("Initializing connection...");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        mConnectThread = new ConnectThread(mDevice);
        mConnectThread.start();
    }


    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        Timber.d("Connected");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();
    }



    public synchronized void stop() {
        Timber.d("Stopping...");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        setState(BluetoothServiceStatus.NONE);
    }


    public void write(byte[] out) {
        ConnectedThread r;
        synchronized (this) {
            if (mState != BluetoothServiceStatus.CONNECTED) {
                return;
            }
            r = mConnectedThread;
        }

        r.write(out);
    }



    public synchronized void connectionFailed() {
        setState(BluetoothServiceStatus.NONE);
    }

    public synchronized void connectionLost() {
        setState(BluetoothServiceStatus.NONE);
    }




    private synchronized void notifyStateChange() {
        mHandler.obtainMessage(HANDLER_MSG_STATE, -1)
                .sendToTarget();
    }




    private class ConnectThread extends Thread {
        private final BluetoothDevice mmDevice;
        private BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice device) {
            Timber.i("Create Connect Thread: " + mDevice.getName());

            mmDevice = device;

            BluetoothSocket tmp = null;
            try {
                tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            }
            catch (IOException e) {
                Timber.e(e, "Bluetooth Socket error");
            }

            mmSocket = tmp;
            setState(BluetoothServiceStatus.CONNECTING);
        }

        @Override
        public void run() {
            Timber.i("Run Connect Thread: " + mmDevice.getName());

            setName("ConnectThread" + mmDevice.getName());

            try {
                mmSocket.connect();
            } catch (IOException e) {
                Timber.w("Trying fallback...");

                try {
                    mmSocket =(BluetoothSocket) mmDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(mmDevice,1);
                    mmSocket.connect();
                } catch (Exception e1) {
                    Timber.e(e, "Fallback error");
                    connectionFailed();
                }
            }

            Timber.d("Done connecting");

            synchronized (BluetoothService.this) {
                mConnectThread = null;
            }

            Timber.d("Synchronized");

            connected(mmSocket, mmDevice);
        }


        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Timber.e(e, "Socket failed to close");
            }
        }
    }





    private class ConnectedThread extends Thread {

        private static final int BUFFER_SIZE = 1024;

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            Timber.d("Create Connected thread: " + mDevice.getName());

            mmSocket = socket;

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = mmSocket.getInputStream();
                tmpOut = mmSocket.getOutputStream();
            } catch (IOException e) {
                Timber.e("Failed to get streams", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;

            setState(BluetoothServiceStatus.CONNECTED);
        }


        @Override
        public void run() {
            Timber.i("Run Connected thread: " + mDevice.getName());

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytes;

            while (mState == BluetoothServiceStatus.CONNECTED) {
                try {
                    bytes = mmInStream.read(buffer);
                    mHandler.obtainMessage(HANDLER_MSG_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Timber.e("Disconnected: " + mDevice.getName(), e);
                    connectionLost();
                    break;
                }
            }

        }



        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);
                mHandler.obtainMessage(HANDLER_MSG_WRITE, -1, -1, buffer)
                    .sendToTarget();
            } catch (IOException e) {
                Timber.e("Write error", e);
            }
        }


        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Timber.e("Socket failed to close", e);
            }
        }


    }

}
