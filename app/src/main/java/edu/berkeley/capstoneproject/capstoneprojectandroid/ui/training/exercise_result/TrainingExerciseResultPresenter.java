package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 18/01/2018.
 */

public class TrainingExerciseResultPresenter<V extends TrainingExerciseResultContract.View, I extends TrainingExerciseResultContract.Interactor> extends BasePresenter<V, I>
    implements TrainingExerciseResultContract.Presenter<V, I> {

    @Inject
    public TrainingExerciseResultPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onMenuClick() {
        if (isViewAttached()) {
            getView().goToMenu();
        }
    }

    @Override
    public void loadExercise() {
        Exercise exercise = getInteractor().getExercise();
        if (isViewAttached()) {
            getView().onExerciseLoaded(exercise);
        }
    }

    @Override
    public void loadExerciseResult() {
        if (isViewAttached()) {
            getView().onExerciseResultLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseResult()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSuccess(new Consumer<ExerciseResult>() {
                    @Override
                    public void accept(ExerciseResult exerciseResult) throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseResultLoaded(exerciseResult);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                        if (isViewAttached()) {
                            getView().onExerciseResultError(throwable);
                        }
                    }
                })
                .subscribe()
        );
    }
}
