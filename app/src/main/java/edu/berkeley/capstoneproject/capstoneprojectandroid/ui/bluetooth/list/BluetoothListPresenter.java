package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.BluetoothConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListPresenter<V extends BluetoothListContract.View, I extends BluetoothListContract.Interactor>
        extends BasePresenter<V, I> implements BluetoothListContract.Presenter<V, I> {

    private static final String TAG = BluetoothListPresenter.class.getSimpleName();

    private boolean mScanning = false;
    private Disposable mScanDisposable;
    private Disposable mConnectionDisposable;

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
                .distinct()
                .subscribe(new Consumer<Rx2BleDevice>() {
                    @Override
                    public void accept(Rx2BleDevice device) throws Exception {
                        getView().addPairedDevice(device);
                    }
                })
        );
    }

    @Override
    public void onStartScanning() {
        Log.d(TAG, "Start scanning");

        // TODO Check Bluetooth status and prompt if disabled

        mScanning = true;
        getView().showScanningProgress();

        mScanDisposable = getInteractor()
                .doDiscovery()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .distinct(new Function<Rx2BleDevice, String>() {
                        @Override
                        public String apply(@NonNull Rx2BleDevice device) throws Exception {
                            return device.getMacAddress();
                        }
                    })
                    .take(BluetoothConstants.SCAN_TIME, TimeUnit.SECONDS)
                    .subscribeWith(new DisposableObserver<Rx2BleDevice>() {
                        @Override
                        public void onNext(@NonNull Rx2BleDevice bluetoothDevice) {
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
                    });
        getCompositeDisposable().add(mScanDisposable);
    }

    @Override
    public void onStopScanning() {
        getView().hideScanningProgress();
        if (mScanDisposable != null) {
            getCompositeDisposable().remove(mScanDisposable);
            mScanDisposable.dispose();
            mScanDisposable = null;
        }
    }

    @Override
    public void onDeviceClick(Rx2BleDevice device) {
        //CapstoneProjectAndroidApplication.getInstance().getFeather52().setBluetoothDevice(device.getBluetoothDevice());
        //getView().startTrainingActivity(device);
        final Rx2BleDevice.ConnectionState connectionState;

        onStopScanning();

        getView().showLoading();

        getInteractor().doSelectDevice(device);
        mConnectionDisposable = getInteractor()
                .doConnectDevice()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeWith(new DisposableObserver<Rx2BleDevice.ConnectionState>() {

                        @Override
                        public void onNext(@NonNull Rx2BleDevice.ConnectionState connectionState) {
                            Log.d(TAG, "Connection status: " + connectionState.toString());
                            switch (connectionState) {
                                case CONNECTED:
                                    Log.d(TAG, "Connection succeeded");
                                    onDeviceConnected();
                                    break;
                                case CONNECTING:
                                case DISCONNECTED:
                                case DISCONNECTING:
                                    Log.e(TAG, "Connection failed");
                                    getView().showError("Connection failed");
                                    break;
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "Connection failed", e);
                            getView().showError("Connection failed");
                            getView().hideLoading();
                        }

                        @Override
                        public void onComplete() {
                            getView().hideLoading();
                        }
                    }
        );

    }

    @Override
    public void onDeviceConnected() {
        mConnectionDisposable = null;
        getView().showMessage("Connected");
        getCompositeDisposable().add(getInteractor()
                .doValidateDevice()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeWith(new DisposableObserver<Boolean>() {
                        @Override
                        public void onNext(@NonNull Boolean b) {
                            if (b) {
                                Log.d(TAG, "Device validated");
                                getView().showMessage("Device validated");
                                getView().onDeviceConnected();
                            }
                            else {
                                Log.e(TAG, "Device not validated");
                                getView().showError("Unknown device");
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "Device validation failed", e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    })
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mConnectionDisposable != null) {
            mConnectionDisposable.dispose();
        }
    }
}
