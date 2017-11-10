package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface Repository<T> {

    void create(T item);
    void create(Iterable<T> items);
    void update(T item);
    void delete(T item);
    void delete(Specification specification);

    Observable<T> query();
    Observable<T> query(Specification specification);
}
