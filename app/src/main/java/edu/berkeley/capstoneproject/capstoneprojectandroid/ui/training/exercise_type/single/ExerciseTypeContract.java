package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 09/12/2017.
 */

public interface ExerciseTypeContract {

    interface View extends IBaseView {

    }

    interface Interactor extends IBaseInteractor {

    }

    interface Presenter<V extends IBaseView, I extends IBaseInteractor> extends IBasePresenter<V, I> {

    }
}
