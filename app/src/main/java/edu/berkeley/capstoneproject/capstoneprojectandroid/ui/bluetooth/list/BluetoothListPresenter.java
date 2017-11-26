package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListPresenter<V extends BluetoothListContract.View, I extends BluetoothListContract.Interactor>
        extends BasePresenter<V, I> implements BluetoothListContract.Presenter<V, I> {

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
        Timber.d("Get paired devices");
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
        Timber.d("Start scanning");

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
                            Timber.d("New device");
                            getView().addScannedDevice(bluetoothDevice);
                        }

                        @Override
                        public void onError(Throwable t) {
                            Timber.e(t, "Scanning error");
                            getView().hideScanningProgress();
                            getView().showError("Scanning error");
                        }

                        @Override
                        public void onComplete() {
                            Timber.d("Scan completed");
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
        mConnectionDisposable = null;
        getView().showMessage("Connected");
        getCompositeDisposable().add(getInteractor()
                .doValidateDevice()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            Timber.d("Device validated");
                            getView().hideLoading();
                            getView().showMessage("Device validated");
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
    public void onDetach() {
        getInteractor().doDisconnect();
        super.onDetach();
    }
}
