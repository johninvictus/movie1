package com.example.android.movieapp1.dagger.modules;

import com.example.android.movieapp1.api.TheMovieDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by invictus on 8/17/17.
 */
@Module
public class TheMovieDbModule {

    @Singleton
    @Provides
    TheMovieDb provideTheMovieDb(Retrofit retrofit) {
        return retrofit.create(TheMovieDb.class);
    }

}
