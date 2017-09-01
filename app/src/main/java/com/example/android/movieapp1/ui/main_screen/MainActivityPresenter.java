package com.example.android.movieapp1.ui.main_screen;

import android.util.Log;

import com.example.android.movieapp1.api.TheMovieDb;
import com.example.android.movieapp1.models.Movie;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;

/**
 * Created by invictus on 8/17/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TheMovieDb theMovieDb;
    MainActivityContract.View view;

    @Inject
    public MainActivityPresenter(TheMovieDb theMovieDb, MainActivityContract.View view) {
        this.theMovieDb = theMovieDb;
        this.view = view;
    }

    @Override
    public void loadMovies(String category) {
        view.showLoading(true);
        theMovieDb.getMoviesByCategory(category)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        view.showLoading(false);
                        view.showMovies(movie);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.showLoading(false);
                        view.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
