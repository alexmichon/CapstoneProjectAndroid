package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypePresenter<V extends ExerciseTypeContract.View, I extends ExerciseTypeContract.Interactor> extends BasePresenter<V, I> implements ExerciseTypeContract.Presenter<V, I> {

    private ExerciseType mExerciseType;

    @Inject
    public ExerciseTypePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    @Override
    public void loadExerciseTypeInfo() {
        if (isViewAttached()) {
            getView().setDescription(mExerciseType.getDescription());
            getView().setDuration(mExerciseType.getDuration());
            getView().setYoutubeVideo(mExerciseType.getYoutubeVideo());
        }
    }
}
