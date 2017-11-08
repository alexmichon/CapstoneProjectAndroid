package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

/**
 * Created by Alex on 07/11/2017.
 */

public interface SplashContract {

    interface View {
        void updateMessage(String msg);
        void promptEnableNetwork();
        void stop();
        void done();
    }

    interface Presenter {
        void start();
    }
}
