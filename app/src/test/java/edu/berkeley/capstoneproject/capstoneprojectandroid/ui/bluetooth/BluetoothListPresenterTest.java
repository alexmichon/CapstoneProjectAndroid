package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

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

    private TestScheduler mTestScheduler;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = new BluetoothListPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable);

        mPresenter.onAttach(mView);
    }

    @Test
    public void onLoadPairedDevicesShouldCallInteractor() {
        doReturn(Observable.empty()).when(mInteractor).doLoadPairedDevices();
        mPresenter.onLoadPairedDevices();
        verify(mInteractor).doLoadPairedDevices();
    }

    @Test
    public void onLoadPairedDevicesShouldUpdateViewWithDevice() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(device)).when(mInteractor).doLoadPairedDevices();
        mPresenter.onLoadPairedDevices();

        mTestScheduler.triggerActions();

        verify(mView).addPairedDevice(device);
    }








    @Test
    public void onStartScanningShouldCallInteractor() {
        doReturn(Observable.empty()).when(mInteractor).doDiscovery();
        mPresenter.onStartScanning();
        verify(mInteractor).doDiscovery();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithLoading() {
        doReturn(Observable.empty()).when(mInteractor).doDiscovery();
        mPresenter.onStartScanning();
        verify(mView).showScanningProgress();
    }

    @Test
    public void onStartScanningShouldUpdateViewWithDevice() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(device)).when(mInteractor).doDiscovery();
        doReturn("").when(device).getMacAddress();
        mPresenter.onStartScanning();

        mTestScheduler.triggerActions();

        verify(mView).addScannedDevice(device);
    }

    @Test
    public void onStartScanningShouldUpdateViewOnFailure() {
        doReturn(Observable.error(new Error())).when(mInteractor).doDiscovery();
        mPresenter.onStartScanning();

        mTestScheduler.triggerActions();

        verify(mView).hideScanningProgress();
        verify(mView).showError(anyString());
    }





    @Test
    public void onDeviceClickShouldCallInteractor() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        mPresenter.onDeviceClick(device);

        verify(mInteractor).doSelectDevice(device);
        verify(mInteractor).doConnectDevice();
    }


    @Test
    public void onDeviceClickShouldStopScanning() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        BluetoothListPresenter spy = Mockito.spy(mPresenter);
        spy.onDeviceClick(device);

        verify(spy).onStopScanning();
    }


    @Test
    public void onDeviceClickShouldUpdateView() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        mPresenter.onDeviceClick(device);

        verify(mView).showLoading();
    }

    @Test
    public void onDeviceClickShouldCheckDeviceOnSuccess() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(Rx2BleDevice.ConnectionState.CONNECTED)).when(mInteractor).doConnectDevice();

        BluetoothListPresenter spy = Mockito.spy(mPresenter);
        spy.onDeviceClick(device);
        doNothing().when(spy).onDeviceConnected();

        mTestScheduler.triggerActions();

        verify(spy).onDeviceConnected();
    }

    @Test
    public void onDeviceClickShouldUpdateViewOnFailure() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(Rx2BleDevice.ConnectionState.DISCONNECTED)).when(mInteractor).doConnectDevice();

        mPresenter.onDeviceClick(device);

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }

    @Test
    public void onDeviceClickShouldUpdateViewOnError() {
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.error(new Error())).when(mInteractor).doConnectDevice();

        mPresenter.onDeviceClick(device);

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }







    @Test
    public void onDeviceConnectedShouldUpdateView() {
        doReturn(Completable.complete()).when(mInteractor).doValidateDevice();

        mPresenter.onDeviceConnected();

        verify(mView).showMessage(anyString());
    }


    @Test
    public void onDeviceConnectedShouldUpdateViewOnSuccess() {
        doReturn(Completable.complete()).when(mInteractor).doValidateDevice();

        mPresenter.onDeviceConnected();

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView, times(2)).showMessage(anyString());
        verify(mView).onDeviceConnected();
    }

    @Test
    public void onDeviceConnectedShouldUpdateViewOnFailure() {
        doReturn(Completable.error(new Error())).when(mInteractor).doValidateDevice();

        mPresenter.onDeviceConnected();

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }



    @After
    public void cleanup() {
        mPresenter.onDetach();
    }
}
