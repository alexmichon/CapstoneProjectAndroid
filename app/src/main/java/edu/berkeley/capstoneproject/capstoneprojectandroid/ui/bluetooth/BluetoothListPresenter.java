package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListPresenter<V extends BluetoothListContract.View, I extends BluetoothListContract.Interactor>
        extends BasePresenter<V, I> implements BluetoothListContract.Presenter<V, I> {

    private static final String TAG = BluetoothListPresenter.class.getSimpleName();

    IBluetoothRepository mBluetoothRepository;

    private boolean mScanning = false;

    @Inject
    public BluetoothListPresenter(I interactor,
                                  ISchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoadPairedDevices() {
        Log.d(TAG, "Get paired devices");
        getCompositeDisposable().add(getInteractor()
            .doLoadPairedDevices()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BluetoothDevice>() {
                    @Override
                    public void accept(BluetoothDevice device) throws Exception {
                        getView().addPairedDevice(device);
                    }
                })
        );
    }

    @Override
    public void onStartScanning() {
        Log.d(TAG, "Start scanning");

        mScanning = true;
        getView().showScanningProgress();

        getCompositeDisposable().add(getInteractor()
                .doDiscovery()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeWith(new DisposableObserver<BluetoothDevice>() {
                        @Override
                        public void onNext(@NonNull BluetoothDevice bluetoothDevice) {
                            Log.d(TAG, "New device");
                            getView().addScannedDevice(bluetoothDevice);
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.e(TAG, "Scanning error", t);
                            getView().hideScanningProgress();
                            getView().showError("Scanning error");
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "Scan completed");
                            getView().hideScanningProgress();
                        }
                    })
        );
    }

    @Override
    public void onStopScanning() {

    }

    @Override
    public void onDeviceClick(BluetoothDevice device) {
        CapstoneProjectAndroidApplication.getInstance().getFeather52().setBluetoothDevice(device);
        getView().startExercisesActivity(device);
    }


}
