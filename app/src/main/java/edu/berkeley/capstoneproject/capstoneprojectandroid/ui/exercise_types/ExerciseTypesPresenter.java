package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesPresenter<V extends ExerciseTypesContract.View, I extends ExerciseTypesContract.Interactor>
        extends BasePresenter<V, I> implements ExerciseTypesContract.Presenter<V, I> {

    private static final String TAG = ExerciseTypesPresenter.class.getSimpleName();

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
        getCompositeDisposable().add(getInteractor()
            .doLoadExerciseTypes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseType>() {
                    @Override
                    public void accept(ExerciseType exerciseType) throws Exception {
                        Timber.d("New exercise type found");
                        getView().addExerciseType(exerciseType);
                    }
                })
        );
    }

    @Override
    public void onExerciseTypeClick(ExerciseType exerciseType) {
        getView().onExerciseTypeSelected(exerciseType);
    }
}
