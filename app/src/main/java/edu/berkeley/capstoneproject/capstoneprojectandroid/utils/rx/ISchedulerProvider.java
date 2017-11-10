package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Alex on 09/11/2017.
 */

public interface ISchedulerProvider {

    Scheduler ui();
    Scheduler io();
}
