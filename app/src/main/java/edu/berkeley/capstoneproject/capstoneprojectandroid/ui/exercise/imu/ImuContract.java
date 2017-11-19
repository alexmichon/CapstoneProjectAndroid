package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.imu;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 17/11/2017.
 */

public class ImuContract {

    interface View extends IBaseView {

    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

    }
}
