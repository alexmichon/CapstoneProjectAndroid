package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

/**
 * Created by Alex on 08/11/2017.
 */

public interface MainContract {

    interface View {
        void showError(String message);
        void startBluetoothListActivity();
    }

    interface Presenter {
        void onStartExerciseClick();
        void onViewResultsClick();
    }
}
