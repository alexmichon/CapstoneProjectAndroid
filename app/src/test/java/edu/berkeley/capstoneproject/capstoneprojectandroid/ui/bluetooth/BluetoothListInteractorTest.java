package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.BluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class BluetoothListInteractorTest {

    private BluetoothListInteractor mInteractor;

    @Mock
    private DataManager mDataManager;

    @Mock
    private IBluetoothHelper mBluetoothHelper;

    @Mock
    private IBluetoothRepository mRepository;

    @Before
    public void setup() {
        mInteractor = new BluetoothListInteractor(mDataManager, mRepository);
        when(mDataManager.getBluetoothHelper()).thenReturn(mBluetoothHelper);
    }

    @Test
    public void doDiscoveryShouldCallRepository() {
        // when
        mInteractor.doDiscovery();

        // then
        verify(mRepository).getScannedDevices();
    }

    @Test
    public void doLoadPairedDevicesShouldCallRepository() {
        // when
        mInteractor.doLoadPairedDevices();

        // then
        verify(mRepository).getPairedDevices();
    }

    @Test
    public void doSelectDeviceShouldUpdateHelper() {
        // given
        Rx2BleDevice device = Mockito.mock(Rx2BleDevice.class);

        // when
        mInteractor.doSelectDevice(device);

        // then
        verify(mBluetoothHelper).setDevice(device);
    }

    @Test
    public void doConnectDeviceShouldCallHelper() {
        // when
        mInteractor.doConnectDevice();

        // then
        verify(mBluetoothHelper).connect(false);
    }

    @Test
    public void doValidateDeviceShouldCallHelper() {
        // when
        mInteractor.doValidateDevice();

        // then
        verify(mBluetoothHelper).validateDevice();
    }
}
