package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseDialog;
import timber.log.Timber;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeDialog extends BaseDialog<ExerciseTypeContract.View, ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>> implements ExerciseTypeContract.View {

    public static final String EXERCISE_TYPE_KEY = "ExerciseType";

    private ExerciseType mExerciseType;

    @BindView(R.id.exercise_type_video)
    VideoView mVideoView;

    @BindView(R.id.exercise_type_description)
    TextView mDescriptionView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mExerciseType = bundle.getParcelable(EXERCISE_TYPE_KEY);
        if (mExerciseType == null) {
            Timber.e("Exercise Type can't be null");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.fragment_exercise_type, null);

        setUnbinder(ButterKnife.bind(this, view));

        mDescriptionView.setText(mExerciseType.getDescription());

        Uri videoUri = mExerciseType.getVideoUri();
        if (videoUri != null) {
            mVideoView.setVideoURI(videoUri);
            mVideoView.setVisibility(View.VISIBLE);

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            mVideoView.start();
        }
        else {
            mVideoView.setVisibility(View.GONE);
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(mExerciseType.getName())
                .setView(view)
                .setPositiveButton("Start",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                )
                .create();
    }

    @NonNull
    @Override
    public ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypePresenter();
    }
}
