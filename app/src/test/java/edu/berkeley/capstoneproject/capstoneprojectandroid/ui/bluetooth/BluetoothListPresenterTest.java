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
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
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

    @Mock
    private Rx2BleDevice mDevice;

    private TestScheduler mTestScheduler;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = Mockito.spy(new BluetoothListPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable));
        doNothing().when(mInteractor).doDisconnect();

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
        doReturn(Single.never()).when(mInteractor).doConnect(mDevice);

        // when
        mPresenter.onDeviceClick(mDevice);

        // then
        verify(mInteractor).doConnect(mDevice);
    }


    @Test
    public void onDeviceClickShouldStopScanning() {
        // given
        doReturn(Single.never()).when(mInteractor).doConnect(mDevice);

        // when
        mPresenter.onDeviceClick(mDevice);

        // then
        verify(mPresenter).onStopScanning();
    }


    @Test
    public void onDeviceClickShouldUpdateView() {
        // given
        doReturn(Single.never()).when(mInteractor).doConnect(mDevice);

        // when
        mPresenter.onDeviceClick(mDevice);

        // then
        verify(mView).showLoading();
    }

    @Test
    public void onDeviceClickShouldCheckDeviceOnSuccess() {
        // given
        Rx2BleConnection connection = Mockito.mock(Rx2BleConnection.class);
        doReturn(Single.just(connection)).when(mInteractor).doConnect(mDevice);
        doNothing().when(mPresenter).onDeviceConnected();

        // when
        mPresenter.onDeviceClick(mDevice);
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).onDeviceConnected();
    }

    @Test
    public void onDeviceClickShouldUpdateViewOnError() {
        // given
        doReturn(Single.error(new Error())).when(mInteractor).doConnect(mDevice);

        // when
        mPresenter.onDeviceClick(mDevice);
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
