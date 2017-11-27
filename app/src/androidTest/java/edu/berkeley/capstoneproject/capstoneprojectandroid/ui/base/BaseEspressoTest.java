package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.app.Activity;
import android.view.WindowManager;

import org.junit.Before;

/**
 * Created by Alex on 27/11/2017.
 */

public class BaseEspressoTest {

    public void unlockScreen(final BaseActivity activity) {
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }

}
