package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.ExerciseBuilderContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 13/01/2018.
 */

public class ExerciseBuilderPresenter<V extends ExerciseBuilderContract.View, I extends ExerciseBuilderContract.Interactor> extends BasePresenter<V, I> implements ExerciseBuilderContract.Presenter<V, I> {

    @Inject
    public ExerciseBuilderPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void selectExerciseType(ExerciseType exerciseType) {
        if (isViewAttached()) {
            getView().onExerciseBuilt();
        }
    }
}
