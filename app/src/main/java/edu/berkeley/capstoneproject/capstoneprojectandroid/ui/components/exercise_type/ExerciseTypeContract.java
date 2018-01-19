package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 09/12/2017.
 */

public interface ExerciseTypeContract {

    interface View extends IBaseView {
        void setYoutubeVideo(String url);
        void setDescription(String description);
    }

    interface Interactor extends IBaseInteractor {
    }

    interface Presenter<V extends IBaseView, I extends IBaseInteractor> extends IBasePresenter<V, I> {
        void setExerciseType(ExerciseType exerciseType);

        void loadExerciseTypeInfo();
    }
}
