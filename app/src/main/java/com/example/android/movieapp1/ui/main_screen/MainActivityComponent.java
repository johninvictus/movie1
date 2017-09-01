package com.example.android.movieapp1.ui.main_screen;

import com.example.android.movieapp1.dagger.components.AppComponent;
import com.example.android.movieapp1.dagger.scope.PerActivity;

import dagger.Component;

/**
 * Created by invictus on 8/17/17.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
