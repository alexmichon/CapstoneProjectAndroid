package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ServiceModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52.Feather52Service2;

/**
 * Created by Alex on 18/11/2017.
 */

@PerService
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(Feather52Service2 service);
}
