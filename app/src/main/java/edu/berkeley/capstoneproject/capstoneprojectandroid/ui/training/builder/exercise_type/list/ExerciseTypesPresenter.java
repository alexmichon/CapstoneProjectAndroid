package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list;

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

public class ExerciseTypesPresenter<V extends ExerciseTypesContract.View, I extends ExerciseTypesContract.Interactor>
        extends BasePresenter<V, I> implements ExerciseTypesContract.Presenter<V, I> {

    private IExerciseTypeRepository mRepository;

    @Inject
    public ExerciseTypesPresenter(I interactor,
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
                        if (isViewAttached()) {
                            getView().onExerciseTypesError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        getView().onExerciseTypesDoneLoading();
                    }
                })
        );
    }

    @Override
    public void onExerciseTypeMore(ExerciseType exerciseType) {
        getView().showExerciseTypeDialog(exerciseType);
    }

    @Override
    public void onExerciseTypeSelect(ExerciseType exerciseType) {
        selectExerciseType(exerciseType);
    }

    @Override
    public void onExerciseTypeDialogBack() {
        if (isViewAttached()) {
            getView().dismissExerciseTypeDialog();
        }
    }

    @Override
    public void onExerciseTypeDialogSelect(ExerciseType exerciseType) {
        selectExerciseType(exerciseType);
    }


    private void selectExerciseType(final ExerciseType exerciseType) {
        if (isViewAttached()) {
            getView().selectExerciseType(exerciseType);
        }
    }
}
