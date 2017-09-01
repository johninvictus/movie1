package com.example.android.movieapp1.ui.main_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.movieapp1.App;
import com.example.android.movieapp1.R;
import com.example.android.movieapp1.adapters.MoviesRecyclerAdapter;
import com.example.android.movieapp1.adapters.RecyclerViewClickListener;
import com.example.android.movieapp1.api.TheMovieDb;
import com.example.android.movieapp1.config.AppConstants;
import com.example.android.movieapp1.models.Movie;
import com.example.android.movieapp1.models.Result;
import com.example.android.movieapp1.ui.movie_detail_screen.DetailActivity;
import com.google.gson.Gson;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, PopupMenu.OnMenuItemClickListener, RecyclerViewClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    private MoviesRecyclerAdapter adapter;

    @Inject
    MainActivityPresenter presenter;

    @Inject
    SharedPreferences preferences;
    @BindView(R.id.loading_progress)
    ProgressBar progressBar;

    private String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent
                .builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.movies_recyclerView);
        adapter = new MoviesRecyclerAdapter(this, this, new ArrayList<Result>());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        fetchMovies();
    }

    private void fetchMovies() {
        category = preferences.getString(getString(R.string.category_key), getString(R.string.category_default));
        presenter.loadMovies(category);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                View menuItemView = findViewById(R.id.action_filter);
                showFilterPopupMenu(menuItemView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showFilterPopupMenu(View view) {
        PopupMenu menu = new PopupMenu(this, view);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.filter_popup, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
        menu.show();
    }

    @Override
    public void showMovies(Movie movie) {
        List<Result> movies = movie.getResults();
        adapter.setMovies(movies);
    }


    @Override
    public void showError(String error) {
        Log.e(LOG_TAG, error);
    }

    @Override
    public void showLoading(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                /**
                 * Show popular
                 * */
                if (!category.equals("popular")) {
                    preferences.edit()
                            .putString(getString(R.string.category_key), getString(R.string.category_default))
                            .commit();

                    fetchMovies();
                }
                return true;
            case R.id.action_top_rated:
                /**
                 * Show top rated
                 * */
                if (!category.equals("top_rated")) {
                    preferences.edit()
                            .putString(getString(R.string.category_key), getString(R.string.category_top_rated))
                            .commit();

                    fetchMovies();
                    Log.e(LOG_TAG, category);
                }

                return true;

            default:
                return false;
        }

    }

    @Override
    public void onClick(int position) {
        Result movie = adapter.getSingleMovie(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra(AppConstants.MOVIE_EXTRA, gson.toJson(movie));
        startActivity(intent);
    }
}
