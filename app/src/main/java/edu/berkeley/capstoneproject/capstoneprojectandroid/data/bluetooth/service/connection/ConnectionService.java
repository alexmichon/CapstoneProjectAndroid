package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.connection;

import android.bluetooth.BluetoothGattService;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.base.BaseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDeviceServices;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 26/11/2017.
 */

public class ConnectionService extends BaseService implements IConnectionService {

    private Disposable mConnectionDisposable;

    @Inject
    public ConnectionService() {

    }


    @Override
    public Single<Rx2BleConnection> connect(final Rx2BleDevice device, final boolean autoconnect) {
        Timber.d("Connection to device");
        return Single.create(new SingleOnSubscribe<Rx2BleConnection>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<Rx2BleConnection> e) throws Exception {
                mConnectionDisposable = device.establishConnection(autoconnect).subscribe(new Consumer<Rx2BleConnection>() {
                    @Override
                    public void accept(Rx2BleConnection connection) throws Exception {
                        e.onSuccess(connection);
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
    public void disconnect() {
        if (mConnectionDisposable != null) {
            mConnectionDisposable.dispose();
            mConnectionDisposable = null;
        }
    }



    @Override
    public Rx2BleDevice.ConnectionState getConnectionState() {
        return getDevice().getConnectionState();
    }


    @Override
    public Observable<Rx2BleDevice.ConnectionState> observeConnectionStateChange() {
        return getDevice().observeConnectionStateChange();
    }






    @Override
    public Completable validateDevice() {
        final Observable<Rx2BleDeviceServices> observable = getConnection().discoverServices();

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
}
