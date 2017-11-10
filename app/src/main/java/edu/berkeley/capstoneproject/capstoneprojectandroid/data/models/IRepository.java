package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models;

import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface IRepository<T> {

    void create(T item);
    void create(Iterable<T> items);
    void update(T item);
    void delete(T item);
    void delete(ISpecification specification);

    Observable<T> query();
    Observable<T> query(ISpecification specification);
}
