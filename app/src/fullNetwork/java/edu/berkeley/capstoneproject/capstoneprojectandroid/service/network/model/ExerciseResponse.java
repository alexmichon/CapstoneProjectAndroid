package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import timber.log.Timber;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseResponse extends BaseResponse<Exercise> {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("exercise_type_id")
    @Expose
    private int mExerciseTypeId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("exercise_type_name")
    @Expose
    private String mExerciseTypeName;

    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getExerciseTypeId() {
        return mExerciseTypeId;
    }

    public void setExerciseTypeId(int exerciseTypeId) {
        mExerciseTypeId = exerciseTypeId;
    }

    @Override
    public Exercise get() {
        Exercise exercise = new Exercise(mId, mExerciseTypeId, mName);
        exercise.setExerciseTypeName(mExerciseTypeName);

        try {
            Timber.d("Created at: %s", mCreatedAt);
            DATE_FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = DATE_FORMATTER.parse(mCreatedAt);
            exercise.setStartDate(date);
        } catch (ParseException e) {
            Timber.w(e);
        }

        return exercise;
    }
}
