package edu.berkeley.capstoneproject.capstoneprojectandroid;

import java.util.List;

/**
 * Created by Alex on 07/11/2017.
 */

public interface Repository<T> {

    void add(T item);
    void add(Iterable<T> items);
    void update(T item);
    void remove(T item);
    void remove(Specification specification);
    List<T> query(Specification specification);
}
