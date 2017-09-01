package com.example.android.movieapp1.ui.main_screen;


import com.example.android.movieapp1.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by invictus on 8/17/17.
 */
@Module
public class MainActivityModule {
    private final MainActivityContract.View mview;

    public MainActivityModule(MainActivityContract.View view) {
        this.mview = view;
    }


    @Provides
    MainActivityContract.View  provideMainActivityView() {
        return mview;
    }

}
