package com.example.android.movieapp1;

import android.app.Application;

import com.example.android.movieapp1.config.AppConstants;
import com.example.android.movieapp1.dagger.components.AppComponent;
import com.example.android.movieapp1.dagger.components.DaggerAppComponent;
import com.example.android.movieapp1.dagger.modules.AppModule;
import com.example.android.movieapp1.dagger.modules.NetModule;
import com.example.android.movieapp1.dagger.modules.StorageModule;

/**
 * Created by invictus on 8/17/17.
 */

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(AppConstants.BASE_URL))
                .storageModule(new StorageModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
