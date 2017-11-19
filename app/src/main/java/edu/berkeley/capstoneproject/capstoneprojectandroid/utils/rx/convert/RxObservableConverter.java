package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.convert;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Alex on 17/11/2017.
 */

public class RxObservableConverter {

    public static <T> Observable<T> convert(final rx.Observable<T> observable1) {
        return RxJavaInterop.toV2Observable(observable1);
    }
}
