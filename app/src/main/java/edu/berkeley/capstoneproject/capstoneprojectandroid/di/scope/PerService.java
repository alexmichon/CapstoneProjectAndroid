package edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Alex on 18/11/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
