package com.example.android.movieapp1.ui.movie_detail_screen;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movieapp1.R;
import com.example.android.movieapp1.config.AppConstants;
import com.example.android.movieapp1.models.Result;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    AppBarLayout appBarLayout;
    ImageView imageView;
    ImageView backdrop_imageview;
    TextView movie_title_textview;
    TextView date_textview;
    TextView rate_textview;
    TextView description_textview;
    TextView votes_textview;
    RatingBar rating;


    int maxScrollRange = 0;
    ActionBar actionBar;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Gson gson = new Gson();
        Result movie = null;
        if (intent != null) {
            movie = gson.fromJson(intent.getStringExtra(AppConstants.MOVIE_EXTRA), Result.class);
        }

        appBarLayout = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        appBarLayout.addOnOffsetChangedListener(this);

        toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        backdrop_imageview = (ImageView) findViewById(R.id.backdrop_imageview);
        movie_title_textview = (TextView) findViewById(R.id.movie_title_textview);
        date_textview = (TextView) findViewById(R.id.date_textview);
        rate_textview = (TextView) findViewById(R.id.rate_textview);
        description_textview = (TextView) findViewById(R.id.description_textview);
        votes_textview = (TextView) findViewById(R.id.votes_textview);
        rating = (RatingBar) findViewById(R.id.rating);

        setSupportActionBar(toolbar);

        imageView.setY(25);
        imageView.requestLayout();
        actionBar = getSupportActionBar();
        actionBar.setTitle("");

        Glide.with(this)
                .load(AppConstants.MOVIE_IMAGE_URL + AppConstants.IMAGE_SIZE_780 + movie.getPosterPath())
                .into(imageView);

        Glide.with(this)
                .load(AppConstants.MOVIE_IMAGE_URL + AppConstants.IMAGE_SIZE_780 + movie.getBackdropPath())
                .into(backdrop_imageview);


        movie_title_textview.setText(movie.getTitle());
        date_textview.setText(movie.getReleaseDate());
        rate_textview.setText(String.valueOf(movie.getVoteAverage()));
        description_textview.setText(movie.getOverview());
        votes_textview.setText(String.valueOf(movie.getVoteCount()));

        float ratingx = (float) ((movie.getVoteAverage() / 10) * 5.0f);
        rating.setRating(ratingx);
    }

    int totalScalingHeight = 0;
    float imageY;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (maxScrollRange == 0) {
            maxScrollRange = appBarLayout.getTotalScrollRange();
            imageY = imageView.getY();
        }

        if (totalScalingHeight == 0) {
            totalScalingHeight = imageView.getHeight();
        }

        int pacentage = (Math.abs(verticalOffset)) * 100 / maxScrollRange + 1;

//        imageView.setPivotX(imageView.getMeasuredWidth()/2);
//        imageView.setPivotY(imageView.getMeasuredHeight());


        imageView.setY(pacentage + 25);


        imageView.getLayoutParams().height = (totalScalingHeight) - pacentage;
        imageView.requestLayout();

    }
}
