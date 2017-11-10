package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import android.os.Build;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.IBluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLe18Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLe21Adapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth.BluetoothLeAdapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class BluetoothListModule extends BaseModule {

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
    IBluetoothRepository provideRepository(BluetoothLeAdapter adapter) {
        return new BluetoothRepository(adapter);
    }

    @Provides
    BluetoothListContract.Interactor provideInteractor(IBluetoothRepository repository) {
        return new BluetoothListInteractor(repository);
    }

    @Provides
    @PerActivity
    BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor>
    providePresenter(BluetoothListContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        return new BluetoothListPresenter(interactor, schedulerProvider, compositeDisposable);
    }
}
