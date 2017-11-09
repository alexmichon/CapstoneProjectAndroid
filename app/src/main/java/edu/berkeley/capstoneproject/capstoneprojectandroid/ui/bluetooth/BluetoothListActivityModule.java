package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.os.Build;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class BluetoothListActivityModule {

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
