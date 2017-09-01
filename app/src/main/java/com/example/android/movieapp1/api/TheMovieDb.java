package com.example.android.movieapp1.api;

import com.example.android.movieapp1.models.Movie;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by invictus on 8/17/17.
 */

public interface TheMovieDb {
    @GET("movie/{category}?")
    Flowable<Movie> getMoviesByCategory(@Path("category") String category);

    @GET("movie/{movie_id}/similar")
    Flowable<Movie> getSimilarMovies(@Path("movie_id") int movie_id);

}
