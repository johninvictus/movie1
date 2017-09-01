package com.example.android.movieapp1.dagger.components;

import android.content.SharedPreferences;

import com.example.android.movieapp1.api.TheMovieDb;
import com.example.android.movieapp1.dagger.modules.AppModule;
import com.example.android.movieapp1.dagger.modules.NetModule;
import com.example.android.movieapp1.dagger.modules.StorageModule;
import com.example.android.movieapp1.dagger.modules.TheMovieDbModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by invictus on 8/17/17.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        StorageModule.class,
        TheMovieDbModule.class
})
public interface AppComponent {
    /**
     * Downstream components need these exposed with the return type
     * Method name does not really matter
     **/
    Retrofit retrofit();
    SharedPreferences sharedPreferences();
    TheMovieDb theMovieDb();
}
