package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultPresenter<V extends ExerciseResultContract.View, I extends ExerciseResultContract.Interactor> extends BasePresenter<V, I> implements ExerciseResultContract.Presenter<V, I> {

    @Inject
    public ExerciseResultPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    public void loadExerciseResult(Exercise exercise) {
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseResult(exercise)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseResult>() {
                    @Override
                    public void accept(ExerciseResult exerciseResult) throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseResultLoaded(exerciseResult);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().showError(throwable);
                        }
                    }
                })
        );
    }

    @Override
    public void onOkClick(ExerciseResult exerciseResult) {

    }
}
