package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

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
        doReturn(Observable.empty()).when(mInteractor).doStartScanning();

        // when
        mPresenter.onStartScanning();

        // then
        verify(mInteractor).doStartScanning();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithLoading() {
        // given
        doReturn(Observable.empty()).when(mInteractor).doStartScanning();

        // when
        mPresenter.onStartScanning();

        // then
        verify(mView).showScanningProgress();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithDevice() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(device)).when(mInteractor).doStartScanning();
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
        doReturn(Observable.error(new Error())).when(mInteractor).doStartScanning();

        // when
        mPresenter.onStartScanning();
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideScanningProgress();
        verify(mView).showError(anyString());
    }




    @Test
    public void onDeviceSelectedShouldStopScanning() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doStopScanning();
        doReturn(Completable.complete()).when(mInteractor).doConnect(any(Rx2BleDevice.class));

        // when
        mPresenter.onDeviceSelected(mDevice);

        // then
        verify(mInteractor).doStopScanning();
    }


    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
