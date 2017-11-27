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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListPresenter<V extends BluetoothListContract.View, I extends BluetoothListContract.Interactor>
        extends BasePresenter<V, I> implements BluetoothListContract.Presenter<V, I> {

    private Disposable mScanDisposable;

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
    public void onDeviceSelected(Rx2BleDevice device) {
        onStopScanning();
    }
}
