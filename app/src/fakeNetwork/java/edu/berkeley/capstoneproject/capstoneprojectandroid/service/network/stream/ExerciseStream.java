package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import timber.log.Timber;

/**
 * Created by Alex on 06/02/2018.
 */

public class ExerciseStream implements IExerciseStream {

    private static final String HEADER_TIMESTAMP = "Timestamp";
    private static final String HEADER_VALUE = "Value";
    private static final String HEADER_METRIC = "Metric";

    private static final String[] HEADERS = new String[] {
            HEADER_TIMESTAMP,
            HEADER_VALUE,
            HEADER_METRIC,
    };

    private final Exercise mExercise;

    private CSVWriter mWriter;

    public ExerciseStream(Exercise exercise) {
        mExercise = exercise;
    }

    @Override
    public Completable connect() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                open();
                send(HEADERS);
            }
        });
    }

    @Override
    public Completable disconnect() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                close();
            }
        });
    }

    @Override
    public void send(String ...string) {
        if (mWriter != null) {
            mWriter.writeNext(string);
        }
    }





    private void open() {
        String filePath = getFilePath();
        File file = new File(filePath);
        try {
            if(file.exists() && !file.isDirectory()){
                Timber.w("File already exists");
                FileWriter mFileWriter = new FileWriter(filePath, true);
                mWriter = new CSVWriter(mFileWriter);
            }
            else {
                mWriter = new CSVWriter(new FileWriter(filePath));
            }
        } catch (IOException e) {
            Timber.e(e);
        }
    }


    private void close() {
        if (mWriter != null) {
            try {
                mWriter.close();
            } catch (IOException e) {
                Timber.e(e);
            }
        }
    }

    private String getFilePath() {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        String fileName = "Exercise - " + dateFormat.format(new Date()) + ".csv";

        return baseDir + File.separator + fileName;
    }






    @Override
    public void doSendMeasurement(Measurement measurement) {
        String data[] = new String[] {
                String.valueOf(measurement.getTimestamp()),
                String.valueOf(measurement.getValue()),
                String.valueOf(measurement.getMetric().getId())
        };

        send(data);
    }
}
