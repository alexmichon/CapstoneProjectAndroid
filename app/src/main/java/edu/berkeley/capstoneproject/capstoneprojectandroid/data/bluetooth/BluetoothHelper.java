package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import android.bluetooth.BluetoothGattService;
import android.util.Log;

import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleConnection;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.BluetoothConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.SensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDeviceServices;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class BluetoothHelper implements IBluetoothHelper {

    private static final String TAG = BluetoothHelper.class.getSimpleName();

    private Rx2BleDevice mDevice;
    private Rx2BleConnection mConnection;
    private Disposable mConnectionDisposable;

    @Inject
    public BluetoothHelper() {

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
            Timber.e("No device: connection aborted");
            return null;
        }

        Timber.d("Connection to device");

        return Observable.create(new ObservableOnSubscribe<Rx2BleDevice.ConnectionState>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Rx2BleDevice.ConnectionState> e) throws Exception {
                Timber.d("Starting connection...");
                Observable<Rx2BleConnection> observable = mDevice.establishConnection(autoconnect);
                mConnectionDisposable = observable.subscribe(new Consumer<Rx2BleConnection>() {
                    @Override
                    public void accept(Rx2BleConnection rx2BleConnection) throws Exception {
                        Timber.d("Connection received");
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
                if (mConnectionDisposable != null) {
                    mConnectionDisposable.dispose();
                    mConnectionDisposable = null;
                }
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
    public Completable validateDevice() {
        final Observable<Rx2BleDeviceServices> observable = mConnection.discoverServices();

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final CompletableEmitter e) throws Exception {
                observable.subscribe(new Consumer<Rx2BleDeviceServices>() {
                    @Override
                    public void accept(Rx2BleDeviceServices rx2BleDeviceServices) throws Exception {
                        rx2BleDeviceServices.getService(BluetoothConstants.UUID_SERVICE).subscribe(new Consumer<BluetoothGattService>() {
                            @Override
                            public void accept(BluetoothGattService gattService) throws Exception {
                                e.onComplete();
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

    @Override
    public IExerciseService getExerciseService() {
        return new ExerciseService(this);
    }

    @Override
    public ISensorService getSensorService() {
        return new SensorService(this);
    }

}
