package edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Alex on 17/11/2017.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
