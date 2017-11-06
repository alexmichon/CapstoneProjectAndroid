package edu.berkeley.capstoneproject.capstoneprojectandroid.network.tasks;

import android.os.Handler;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.TimeUnit;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers.UserHelper;

/**
 * Created by Alex on 05/11/2017.
 */

public class LoginThread extends Thread {

    private static final String TAG = LoginThread.class.getSimpleName();

    public static final int HANDLER_MESSAGE_LOGIN = 0;
    public static final int HANDLER_LOGIN_SUCCESS = 0;
    public static final int HANDLER_LOGIN_FAILURE = 1;

    private final Handler mHandler;
    private final User mUser;

    private RequestFuture mFuture;

    public LoginThread(Handler handler, User user) {
        mHandler = handler;
        mUser = user;
    }

    @Override
    public void run() {
        try {
            mFuture = UserHelper.login(mUser.getEmail(), mUser.getPassword());
            mFuture.get(30, TimeUnit.SECONDS);

            Log.d(TAG, "Authenticated");
            mUser.setAuthenticated(true);

            mHandler.obtainMessage(HANDLER_MESSAGE_LOGIN, HANDLER_LOGIN_SUCCESS).sendToTarget();

        } catch (Exception e) {
            Log.e(TAG, "Login Error", e);
            mUser.setAuthenticated(false);
            mHandler.obtainMessage(HANDLER_MESSAGE_LOGIN, HANDLER_LOGIN_FAILURE).sendToTarget();
        }
    }

    public void cancel() {
        mFuture.cancel(true);
    }
}
