package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullBluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullNetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullBluetoothModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullNetworkModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 27/11/2017.
 */

public class FullApplication extends CapstoneProjectAndroidApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new FullAppModule(this);

        BluetoothComponent bluetoothComponent = DaggerFullBluetoothComponent.builder()
                .appModule(appModule)
                .fullBluetoothModule(new FullBluetoothModule())
                .build();

        NetworkComponent networkComponent = DaggerFullNetworkComponent.builder()
                .fullNetworkModule(new FullNetworkModule())
                .build();

        mAppComponent = DaggerAppComponent
                .builder()
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
                .activityModule(new ActivityModule(activity))
                .appComponent(getAppComponent())
                .build();
    }
}
