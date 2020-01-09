package com.example.androidexpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidexpert.R;
import com.example.androidexpert.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {

        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.movie_listview, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        Movie movie = (Movie) getItem(position);

        Glide.with(context)
                .load(movie.getPhoto())
                .into(viewHolder.photo);
        viewHolder.title.setText(movie.getTitle());
        String[] genreSplit = movie.getGenre().split(",");
        viewHolder.genre.setText(genreSplit[0]); //get main genre
        viewHolder.year.setText(movie.getReleaseYear());
        viewHolder.rating.setText(movie.getRating());

        return view;
    }

    public class ViewHolder {
        @BindView(R.id.img_photo)
        ImageView photo;
        @BindView(R.id.txt_title)
        TextView title;
        @BindView(R.id.txt_year)
        TextView year;
        @BindView(R.id.txt_genre)
        TextView genre;
        @BindView(R.id.txt_rating)
        TextView rating;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
