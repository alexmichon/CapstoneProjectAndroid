package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class BluetoothListInteractorTest {

    private BluetoothListInteractor mInteractor;

    @Mock
    private IBluetoothManager mBluetoothManager;

    @Mock
    private Rx2BleDevice mDevice;

    @Before
    public void setup() {
        mInteractor = new BluetoothListInteractor(mBluetoothManager);
    }

    @Test
    public void doStartScanningShouldCallManager() {
        // when
        mInteractor.doStartScanning();

        // then
        verify(mBluetoothManager).doStartScanning();
    }

    @Test
    public void doStopScanningShouldCallManager() {
        // when
        mInteractor.doStopScanning();

        // then
        verify(mBluetoothManager).doStopScanning();
    }

    @Test
    public void doLoadPairedDevicesShouldCallManager() {
        // when
        mInteractor.doLoadPairedDevices();

        // then
        verify(mBluetoothManager).doGetPairedDevices();
    }
}
