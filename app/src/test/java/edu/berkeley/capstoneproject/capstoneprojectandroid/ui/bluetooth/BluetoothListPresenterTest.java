package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Alex on 22/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class BluetoothListPresenterTest {

    private BluetoothListPresenter<BluetoothListContract.View, BluetoothListContract.Interactor> mPresenter;

    @Mock
    private BluetoothListContract.Interactor mInteractor;

    @Mock
    private BluetoothListContract.View mView;

    @Mock
    private Rx2BleDevice mDevice;

    private TestScheduler mTestScheduler;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = Mockito.spy(new BluetoothListPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable));

        mPresenter.attachView(mView);
    }

    @Test
    public void onLoadPairedDevicesShouldCallInteractor() {
        // given
        doReturn(Observable.empty()).when(mInteractor).doLoadPairedDevices();

        // when
        mPresenter.onLoadPairedDevices();

        // then
        verify(mInteractor).doLoadPairedDevices();
    }

    @Test
    public void onLoadPairedDevicesShouldUpdateViewWithDevice() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(device)).when(mInteractor).doLoadPairedDevices();

        // when
        mPresenter.onLoadPairedDevices();
        mTestScheduler.triggerActions();

        // then
        verify(mView).addPairedDevice(device);
    }








    @Test
    public void onStartScanningShouldCallInteractor() {
        // given
        doReturn(Observable.empty()).when(mInteractor).doDiscovery();

        // when
        mPresenter.onStartScanning();

        // then
        verify(mInteractor).doDiscovery();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithLoading() {
        // given
        doReturn(Observable.empty()).when(mInteractor).doDiscovery();

        // when
        mPresenter.onStartScanning();

        // then
        verify(mView).showScanningProgress();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithDevice() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(device)).when(mInteractor).doDiscovery();
        doReturn("").when(device).getMacAddress();

        // when
        mPresenter.onStartScanning();
        mTestScheduler.triggerActions();

        // then
        verify(mView).addScannedDevice(device);
    }

    @Test
    public void onStartScanningShouldUpdateViewOnFailure() {
        // given
        doReturn(Observable.error(new Error())).when(mInteractor).doDiscovery();

        // when
        mPresenter.onStartScanning();
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideScanningProgress();
        verify(mView).showError(anyString());
    }



    @Test
    public void onStopScanningShouldDisposeSubscription() {
        // given
        Observable observable = Observable.never();
        doReturn(observable).when(mInteractor).doDiscovery();

        TestObserver observer = observable.test();

        mPresenter.onStartScanning();

        // when
        mPresenter.onStopScanning();

        // then
        // TODO
        //assertFalse(observer.hasSubscription());
    }



    @Test
    public void onDeviceSelectedShouldStopScanning() {
        // when
        mPresenter.onDeviceSelected(mDevice);

        // then
        verify(mPresenter).onStopScanning();
    }


    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
