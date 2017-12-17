package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingPresenter<V extends TrainingContract.View, I extends TrainingContract.Interactor>
        extends BasePresenter<V,I> implements TrainingContract.Presenter<V, I> {

    @Inject
    public TrainingPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener getNavigationListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_training_bluetooth_device:
                        getView().showMessage("You're already here !");
                        return true;
                }
                return false;
            }
        };
    }


    @Override
    public void onDeviceSelected(Rx2BleDevice device) {
        //CapstoneProjectAndroidApplication.getInstance().getFeather52().setBluetoothDevice(device.getBluetoothDevice());
        //getView().startTrainingActivity(device);
        final Rx2BleDevice.ConnectionState connectionState;

        getView().showLoading();

        getCompositeDisposable().add(getInteractor()
                .doConnect(device)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Rx2BleConnection>() {

                               @Override
                               public void accept(Rx2BleConnection connection) throws Exception {
                                   Timber.d("Connection succeeded");
                                   onDeviceConnected();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Timber.e(throwable, "Connection failed");
                                   getView().showError("Connection failed");
                                   getView().hideLoading();
                               }
                           }
                ));

    }

    @Override
    public void onDeviceConnected() {
        getView().showMessage("Connected");
        getCompositeDisposable().add(getInteractor()
                .doValidateDevice()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Device validated");
                        getView().onDeviceConnected();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e("Device not validated");
                        getView().hideLoading();
                        getView().showError("Unknown device");
                    }
                })
        );
    }

    @Override
    public void setExerciseGoal(ExerciseGoal exerciseGoal) {

    }
}
