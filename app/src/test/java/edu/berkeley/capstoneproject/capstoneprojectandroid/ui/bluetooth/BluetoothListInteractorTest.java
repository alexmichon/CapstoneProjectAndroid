package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.connection.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
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
    private IDeviceService mDeviceService;

    @Mock
    private IConnectionService mConnectionService;

    @Mock
    private Rx2BleDevice mDevice;

    @Before
    public void setup() {
        mInteractor = new BluetoothListInteractor(mDataManager);
        when(mDataManager.getBluetoothHelper()).thenReturn(mBluetoothHelper);
        when(mBluetoothHelper.getDeviceService()).thenReturn(mDeviceService);
        when(mBluetoothHelper.getConnectionService()).thenReturn(mConnectionService);
    }

    @Test
    public void doDiscoveryShouldCallRepository() {
        // when
        mInteractor.doDiscovery();

        // then
        verify(mDeviceService).getScannedDevices();
    }

    @Test
    public void doLoadPairedDevicesShouldCallRepository() {
        // when
        mInteractor.doLoadPairedDevices();

        // then
        verify(mDeviceService).getPairedDevices();
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
        // given
        doReturn(Single.never()).when(mConnectionService).connect(eq(mDevice), anyBoolean());

        // when
        mInteractor.doConnect(mDevice);

        // then
        verify(mConnectionService).connect(mDevice, false);
    }

    @Test
    public void doValidateDeviceShouldCallHelper() {
        // when
        mInteractor.doValidateDevice();

        // then
        verify(mConnectionService).validateDevice();
    }
}
