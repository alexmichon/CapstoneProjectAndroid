package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesPresenter extends BasePresenter<ExerciseTypesContract.View> implements ExerciseTypesContract.Presenter {

    private static final String TAG = ExerciseTypesPresenter.class.getSimpleName();

    private ExerciseTypeRepository mRepository;

    @Inject
    public ExerciseTypesPresenter(ExerciseTypesContract.View view, ExerciseTypeRepository repository) {
        super(view);
        mRepository = repository;
    }


    @Override
    public void loadExerciseTypes() {
        Log.d(TAG, "Loading exercise types");
        mRepository.query()
                .observeOn(getObservingScheduler())
                .subscribeOn(getSubscribingScheduler())
                .subscribe(new Consumer<ExerciseType>() {
                    @Override
                    public void accept(ExerciseType exerciseType) throws Exception {
                        Log.d(TAG, "New exercise type found");
                        mView.addExerciseType(exerciseType);
                    }
                });
    }

    @Override
    public void onExerciseTypeClick(ExerciseType exerciseType) {
        mView.startExerciseTypeActivity(exerciseType);
    }
}
