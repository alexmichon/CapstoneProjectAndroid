package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullBluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullNetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullTestAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullTestBluetoothModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullTestNetworkModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 04/12/2017.
 */

public class FullTestApplication extends FullApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        FullTestAppModule appModule = new FullTestAppModule(this);

        BluetoothComponent bluetoothComponent = DaggerFullBluetoothComponent.builder()
                .appModule(appModule)
                .fullBluetoothModule(new FullTestBluetoothModule())
                .build();

        NetworkComponent networkComponent = DaggerFullNetworkComponent.builder()
                .fullNetworkModule(new FullTestNetworkModule())
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(appModule)
                .bluetoothComponent(bluetoothComponent)
                .networkComponent(networkComponent)
                .build();

        mAppComponent.inject(this);
    }

    @Override
    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerActivityComponent.builder()
                .activityModule(new TestActivityModule(activity))
                .appComponent(getAppComponent())
                .build();
    }
}
