package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypePresenter<V extends ExerciseTypeContract.View, I extends ExerciseTypeContract.Interactor>
        extends BasePresenter<V, I> implements ExerciseTypeContract.Presenter<V, I> {

    private static final String TAG = ExerciseTypePresenter.class.getSimpleName();

    private IExerciseTypeRepository mRepository;

    @Inject
    public ExerciseTypePresenter(I interactor,
                                 ISchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onLoadExerciseTypes() {
        Timber.d("Loading exercise types");

        getView().onExerciseTypesLoading();

        getCompositeDisposable().add(getInteractor()
            .doLoadExerciseTypes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<ExerciseType>() {
                    @Override
                    public void onNext(@NonNull ExerciseType exerciseType) {
                        Timber.d("New exercise type found");
                        getView().addExerciseType(exerciseType);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().showError(e);
                    }

                    @Override
                    public void onComplete() {
                        getView().onExerciseTypesDoneLoading();
                    }
                })
        );
    }

    @Override
    public void onExerciseTypeClick(ExerciseType exerciseType) {
        getView().onExerciseTypeSelected(exerciseType);
    }
}
