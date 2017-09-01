package com.example.android.movieapp1.ui.main_screen;

import com.example.android.movieapp1.models.Movie;
import com.example.android.movieapp1.models.Result;

import java.util.List;

/**
 * Created by invictus on 8/17/17.
 */

public interface MainActivityContract {
    interface View {
        void showMovies(Movie movie);

        void showError(String error);
        void showLoading(boolean show);
    }

    interface Presenter {
        void loadMovies(String category);
    }
}
