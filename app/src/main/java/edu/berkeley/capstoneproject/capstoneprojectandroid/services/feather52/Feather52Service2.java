package edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleConnection;

import java.util.UUID;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.BluetoothConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.BaseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDeviceServices;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Alex on 11/11/2017.
 */

public class Feather52Service2 extends BaseService implements IFeather52Service {

    private static final String TAG = Feather52Service2.class.getSimpleName();

    private Rx2BleDevice mDevice;
    private Rx2BleConnection mConnection;
    private Disposable mConnectionDisposable;

    public static void start(Context context) {
        context.startService(new Intent(context, Feather52Service2.class));
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, Feather52Service2.class));
    }

    @Inject
    public Feather52Service2(CapstoneProjectAndroidApplication application) {
        super(application);
        getServiceComponent().inject(this);
    }

    @Override
    public void setDevice(Rx2BleDevice device) {
        if (mConnection != null) {
            disconnect();
            mConnection = null;
        }
        mDevice = device;
    }

    @Override
    public Rx2BleDevice getDevice() {
        return mDevice;
    }



    @Override
    public Observable<Rx2BleDevice.ConnectionState> connect(final boolean autoconnect) {
        if (mDevice == null) {
            Log.e(TAG, "No device: connection aborted");
            return null;
        }

        Log.d(TAG, "Connection to device");

        return Observable.create(new ObservableOnSubscribe<Rx2BleDevice.ConnectionState>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Rx2BleDevice.ConnectionState> e) throws Exception {
                Log.d(TAG, "Starting connection...");
                Observable<Rx2BleConnection> observable = mDevice.establishConnection(autoconnect);
                mConnectionDisposable = observable.subscribe(new Consumer<Rx2BleConnection>() {
                    @Override
                    public void accept(Rx2BleConnection rx2BleConnection) throws Exception {
                        Log.d(TAG, "Connection received");
                        mConnection = rx2BleConnection;
                        e.onNext(mDevice.getConnectionState());
                        e.onComplete();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        e.onError(throwable);
                    }
                });
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                mConnectionDisposable.dispose();
                mConnectionDisposable = null;
            }
        });
    }

    @Override
    public Observable<Rx2BleDevice.ConnectionState> disconnect() {
        if (mConnectionDisposable != null) {
            mConnectionDisposable.dispose();
            mConnectionDisposable = null;
        }
        return Observable.just(Rx2BleDevice.ConnectionState.DISCONNECTED);
    }



    @Override
    public Rx2BleDevice.ConnectionState getConnectionState() {
        return mDevice.getConnectionState();
    }

    @Override
    public Observable<Rx2BleDevice.ConnectionState> observeConnectionStateChange() {
        final Observable<RxBleConnection.RxBleConnectionState> observable = mDevice.observeConnectionStateChange();
        final Disposable disposable[] = {null};

        return Observable.create(new ObservableOnSubscribe<Rx2BleDevice.ConnectionState>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Rx2BleDevice.ConnectionState> e) throws Exception {
                disposable[0] = observable.subscribe(new Consumer<RxBleConnection.RxBleConnectionState>() {
                    @Override
                    public void accept(RxBleConnection.RxBleConnectionState rxBleConnectionState) throws Exception {
                        e.onNext(mDevice.getConnectionState());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        e.onError(throwable);
                    }
                });
            }
        });
    }






    @Override
    public Observable<Boolean> validateDevice() {
        final Observable<Rx2BleDeviceServices> observable = mConnection.discoverServices();

        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Boolean> e) throws Exception {
                observable.subscribeWith(new DisposableObserver<Rx2BleDeviceServices>() {
                    @Override
                    public void onNext(@NonNull final Rx2BleDeviceServices rxBleDeviceServices) {
                        rxBleDeviceServices.getService(BluetoothConstants.UUID_SERVICE).subscribe(new Consumer<BluetoothGattService>() {
                            @Override
                            public void accept(BluetoothGattService gattService) throws Exception {
                                e.onNext(true);
                                e.onComplete();
                                dispose();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                e.onNext(false);
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        e.onError(throwable);
                    }

                    @Override
                    public void onComplete() {
                        e.onComplete();
                    }
                });
            }
        });
    }




    @Override
    public Observable<byte[]> readCharacteristic(UUID uuid) {
        return mConnection.readCharacteristic(uuid);
    }

    @Override
    public Observable<byte[]> writeCharacteristic(UUID uuid, byte[] bytes) {
        return mConnection.writeCharacteristic(uuid, bytes);
    }

    @Override
    public Observable<Observable<byte[]>> setupNotification(UUID uuid, NotificationSetupMode setupMode) {
        return mConnection.setupNotification(uuid, setupMode);
    }


}
