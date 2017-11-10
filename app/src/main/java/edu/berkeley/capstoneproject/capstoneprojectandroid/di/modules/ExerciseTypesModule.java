package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class ExerciseTypesModule extends BaseModule {

    @Provides
    ExerciseTypesContract.View provideView(ExerciseTypesActivity activity) {
        return activity;
    }

    @Provides
    IExerciseTypeRepository provideRepository() {
        return new ExerciseTypeRepository();
    }

    @Provides
    ExerciseTypesContract.Interactor provideInteractor(IExerciseTypeRepository repository) {
        return new ExerciseTypesInteractor(repository);
    }

    @Provides
    @PerActivity
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor>
    providePresenter(ExerciseTypesContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        return new ExerciseTypesPresenter(interactor, schedulerProvider, compositeDisposable);
    }
}
