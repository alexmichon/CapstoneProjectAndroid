package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

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

        getView().showScanningProgress();

        getCompositeDisposable().add(getInteractor().doStartScanning()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .distinct(new Function<Rx2BleDevice, String>() {
                    @Override
                    public String apply(@NonNull Rx2BleDevice device) throws Exception {
                        return device.getMacAddress();
                    }
                })
                .take(BluetoothConstants.SCAN_TIME, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Rx2BleDevice>() {
                    @Override
                    public void accept(Rx2BleDevice rx2BleDevice) throws Exception {
                        Timber.d("New device");
                        getView().addScannedDevice(rx2BleDevice);
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Scan completed");
                        getView().hideScanningProgress();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Scanning error");
                        getView().hideScanningProgress();
                        getView().showError("Scanning error");
                    }
                })
                .subscribe()
        );
    }

    @Override
    public void stopScanning() {
        if (isViewAttached()) {
            getView().hideScanningProgress();
        }

        getInteractor().doStopScanning()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe();
    }

    @Override
    public void onDeviceSelected(Rx2BleDevice device) {
        stopScanning();

        if (isViewAttached()) {
            getView().onDeviceConnecting();
        }

        getCompositeDisposable().add(getInteractor()
                .doConnect(device)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Connection succeeded");
                        onDeviceConnected();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Connection failed");

                        if (isViewAttached()) {
                            getView().showError("Connection failed");
                            getView().hideLoading();
                        }
                    }
                })
                .subscribe());
    }

    private void onDeviceConnected() {
        if (isViewAttached()) {
            getView().showMessage("Connected");
        }

        getCompositeDisposable().add(getInteractor().doValidateDevice()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Device validated");

                        if (isViewAttached()) {
                            getView().onDeviceConnected();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e("Device not validated");

                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().showError("Unknown device");
                        }
                    }
                })
        );
    }
}
