package com.example.android.movieapp1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movieapp1.R;
import com.example.android.movieapp1.config.AppConstants;
import com.example.android.movieapp1.models.Movie;
import com.example.android.movieapp1.models.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by invictus on 8/17/17.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Result> movies = new ArrayList<>();
    RecyclerViewClickListener clickListener;

    public MoviesRecyclerAdapter(Context context, RecyclerViewClickListener listener, List<Result> movies) {
        this.context = context;
        this.movies = movies;
        clickListener = listener;
    }

    public void setMovies(List<Result> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public Result getSingleMovie(int position) {
        return movies.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMoviewDetails(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.poster_imageview)
        ImageView posterImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void setMoviewDetails(Result result) {

            Glide.with(context)
                    .load(AppConstants.MOVIE_IMAGE_URL + AppConstants.IMAGE_SIZE_780 + result.getPosterPath())
                    .into(posterImageView);
        }


        @Override
        public void onClick(View view) {
            clickListener.onClick(getAdapterPosition());
        }
    }
}
