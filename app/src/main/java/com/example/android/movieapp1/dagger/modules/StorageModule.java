package com.example.android.movieapp1.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by invictus on 8/17/17.
 */
@Module
public class StorageModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
