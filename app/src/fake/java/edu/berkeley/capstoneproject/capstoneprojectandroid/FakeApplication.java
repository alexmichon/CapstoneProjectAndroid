package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeBluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeNetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeSessionComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.SessionComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeBluetoothModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeNetworkModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeSessionModule;

/**
 * Created by Alex on 27/11/2017.
 */

public class FakeApplication extends CapstoneProjectAndroidApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new FakeAppModule(this);

        BluetoothComponent bluetoothComponent = DaggerFakeBluetoothComponent.builder()
                .appModule(appModule)
                .fakeBluetoothModule(new FakeBluetoothModule())
                .build();

        NetworkComponent networkComponent = DaggerFakeNetworkComponent.builder()
                .appModule(appModule)
                .fakeNetworkModule(new FakeNetworkModule())
                .build();

        SessionComponent sessionComponent = DaggerFakeSessionComponent.builder()
                .appModule(appModule)
                .fakeSessionModule(new FakeSessionModule())
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(appModule)
                .bluetoothComponent(bluetoothComponent)
                .networkComponent(networkComponent)
                .sessionComponent(sessionComponent)
                .build();
    }
}
