package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListPresenter extends BasePresenter<BluetoothListContract.View> implements BluetoothListContract.Presenter {

    private static final String TAG = BluetoothListPresenter.class.getSimpleName();

    BluetoothRepository mBluetoothRepository;

    private boolean mScanning = false;
    private Observable<BluetoothDevice> mScanSubscription;
    private Observable<BluetoothDevice> mPairSubscription;

    @Inject
    public BluetoothListPresenter(BluetoothListContract.View view, BluetoothRepository bluetoothRepository) {
        super(view);
        mBluetoothRepository = bluetoothRepository;
    }

    @Override
    public void startDiscovery() {
        Log.d(TAG, "Start scanning");
        mScanning = true;
        mScanSubscription = mBluetoothRepository.getScannedDevices();
        mScanSubscription.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BluetoothDevice>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showScanningProgress();
                    }

                    @Override
                    public void onNext(@NonNull BluetoothDevice bluetoothDevice) {
                        Log.d(TAG, "New device");
                        mView.addScannedDevice(bluetoothDevice);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "Scanning error", t);
                        mView.hideScanningProgress();
                        mView.showError("Scanning error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Scan completed");
                        mView.hideScanningProgress();
                    }
                });
    }

    @Override
    public void cancelDiscovery() {
        mScanSubscription.unsubscribeOn(Schedulers.io());
    }

    @Override
    public void getPairedDevices() {
        Log.d(TAG, "Get paired devices");
        mPairSubscription = mBluetoothRepository.getPairedDevices();
        mPairSubscription.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BluetoothDevice>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BluetoothDevice device) {
                        mView.addPairedDevice(device);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "Pairing error", e);
                        mView.showError("Pairing error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDeviceClick(BluetoothDevice device) {
        CapstoneProjectAndroidApplication.getInstance().getFeather52().setBluetoothDevice(device);
        mView.startExercisesActivity(device);
    }


}
