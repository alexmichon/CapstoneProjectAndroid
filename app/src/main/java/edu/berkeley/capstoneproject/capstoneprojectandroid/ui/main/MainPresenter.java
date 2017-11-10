package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();


    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void onStartExerciseClick() {
        mView.startBluetoothListActivity();
    }

    @Override
    public void onViewResultsClick() {
        mView.showError("Not implemented yet !");
    }
}
