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

        mPresenter = Mockito.spy(new BluetoothListPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable));

        mPresenter.onAttach(mView);
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
    public void onDeviceClickShouldCallInteractor() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        // when
        mPresenter.onDeviceClick(device);

        // then
        verify(mInteractor).doSelectDevice(device);
        verify(mInteractor).doConnectDevice();
    }


    @Test
    public void onDeviceClickShouldStopScanning() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        // when
        mPresenter.onDeviceClick(device);

        // then
        verify(mPresenter).onStopScanning();
    }


    @Test
    public void onDeviceClickShouldUpdateView() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.empty()).when(mInteractor).doConnectDevice();

        // when
        mPresenter.onDeviceClick(device);

        // then
        verify(mView).showLoading();
    }

    @Test
    public void onDeviceClickShouldCheckDeviceOnSuccess() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(Rx2BleDevice.ConnectionState.CONNECTED)).when(mInteractor).doConnectDevice();
        doNothing().when(mPresenter).onDeviceConnected();

        // when
        mPresenter.onDeviceClick(device);
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).onDeviceConnected();
    }

    @Test
    public void onDeviceClickShouldUpdateViewOnFailure() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.just(Rx2BleDevice.ConnectionState.DISCONNECTED)).when(mInteractor).doConnectDevice();

        // when
        mPresenter.onDeviceClick(device);
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }

    @Test
    public void onDeviceClickShouldUpdateViewOnError() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);
        doReturn(Observable.error(new Error())).when(mInteractor).doConnectDevice();

        // when
        mPresenter.onDeviceClick(device);
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }







    @Test
    public void onDeviceConnectedShouldUpdateView() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doValidateDevice();

        // when
        mPresenter.onDeviceConnected();

        // then
        verify(mView).showMessage(anyString());
    }


    @Test
    public void onDeviceConnectedShouldUpdateViewOnSuccess() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doValidateDevice();

        // when
        mPresenter.onDeviceConnected();
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideLoading();
        verify(mView, times(2)).showMessage(anyString());
        verify(mView).onDeviceConnected();
    }

    @Test
    public void onDeviceConnectedShouldUpdateViewOnFailure() {
        // given
        doReturn(Completable.error(new Error())).when(mInteractor).doValidateDevice();

        // when
        mPresenter.onDeviceConnected();
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideLoading();
        verify(mView).showError(anyString());
    }



    @After
    public void cleanup() {
        mPresenter.onDetach();
    }
}
