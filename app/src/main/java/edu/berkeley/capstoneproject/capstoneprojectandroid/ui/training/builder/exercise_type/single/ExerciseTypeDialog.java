package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.single;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseDialog;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.ExerciseTypeContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.ExerciseTypeFragment;
import timber.log.Timber;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeDialog extends DialogFragment implements ExerciseTypeFragment.ExerciseTypeFragmentListener {

    public static final String EXERCISE_TYPE_KEY = "ExerciseType";

    private ExerciseType mExerciseType;
    private ExerciseTypeDialogListener mListener;

    @BindView(R.id.dialog_exercise_type_name)
    TextView mNameView;

    @BindView(R.id.dialog_exercise_type_fragment)
    FrameLayout mExerciseTypeFrame;

    @BindView(R.id.dialog_exercise_type_back_button)
    Button mBackButton;

    @BindView(R.id.dialog_exercise_type_start_button)
    Button mStartButton;


    public static ExerciseTypeDialog newInstance(ExerciseType exerciseType, ExerciseTypeDialogListener listener) {
        ExerciseTypeDialog dialog = new ExerciseTypeDialog();

        Bundle args = new Bundle();
        args.putParcelable(EXERCISE_TYPE_KEY, exerciseType);
        dialog.setArguments(args);

        dialog.setListener(listener);

        return dialog;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mExerciseType = bundle.getParcelable(EXERCISE_TYPE_KEY);
        if (mExerciseType == null) {
            Timber.e("Exercise Type can't be null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_exercise_type, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        ButterKnife.bind(this, view);

        mNameView.setText(mExerciseType.getName());

        getChildFragmentManager().beginTransaction()
                .replace(R.id.dialog_exercise_type_fragment, ExerciseTypeFragment.newInstance(mExerciseType, this))
                .commit();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onExerciseTypeDialogBack();
                }
            }
        });
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onExerciseTypeDialogSelect(mExerciseType);
                }
            }
        });

        return view;
    }

    public void setListener(ExerciseTypeDialogListener listener) {
        mListener = listener;
    }

    public interface ExerciseTypeDialogListener {
        void onExerciseTypeDialogBack();
        void onExerciseTypeDialogSelect(ExerciseType exerciseType);
    }
}
