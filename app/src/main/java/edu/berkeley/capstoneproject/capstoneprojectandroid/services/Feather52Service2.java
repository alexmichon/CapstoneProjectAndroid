package edu.berkeley.capstoneproject.capstoneprojectandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Alex on 11/11/2017.
 */

public class Feather52Service2 extends Service implements Feather52Contract.Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
