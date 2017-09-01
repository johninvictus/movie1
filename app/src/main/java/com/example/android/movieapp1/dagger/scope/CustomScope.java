package com.example.android.movieapp1.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by invictus on 8/17/17.
 */

@Qualifier
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
