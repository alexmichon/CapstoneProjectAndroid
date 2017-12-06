package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeBluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeNetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeNetworkModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestBluetoothModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 04/12/2017.
 */

public class FakeTestApplication extends TestApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        FakeTestAppModule appModule = new FakeTestAppModule(this);

        BluetoothComponent bluetoothComponent = DaggerFakeBluetoothComponent.builder()
                .appModule(appModule)
                .fakeBluetoothModule(new FakeTestBluetoothModule())
                .build();

        NetworkComponent networkComponent = DaggerFakeNetworkComponent.builder()
                .fakeNetworkModule(new FakeNetworkModule())
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
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerActivityComponent.builder()
                .activityModule(new TestActivityModule(activity))
                .appComponent(getAppComponent())
                .build();
    }
}
