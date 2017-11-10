package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import android.os.Build;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLe18Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLe21Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLeAdapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothRepositoryImpl;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class BluetoothListModule {

    @Provides
    BluetoothListContract.View provideView(BluetoothListActivity bluetoothListActivity) {
        return bluetoothListActivity;
    }

    @Provides
    BluetoothLeAdapter provideBluetoothLeAdapter() {
        if(Build.VERSION.SDK_INT >= 21) {
            return new BluetoothLe21Adapter();
        }
        else {
            return new BluetoothLe18Adapter();
        }
    }

    @Provides
    BluetoothRepository provideRepository(BluetoothLeAdapter adapter) {
        return new BluetoothRepositoryImpl(adapter);
    }

    @Provides
    BluetoothListContract.Presenter providePresenter(BluetoothListContract.View view, BluetoothRepository repository) {
        return new BluetoothListPresenter(view, repository);
    }
}
