package com.example.androidexpert3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidexpert3.R;
import com.example.androidexpert3.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final ArrayList<Movie> movies = new ArrayList<>();
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public void setData(ArrayList<Movie> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickCallBack.onItemClicked(movies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView photo;
        @BindView(R.id.txt_title)
        TextView title;
        @BindView(R.id.txt_date)
        TextView year;
        @BindView(R.id.txt_genre)
        TextView genre;
        @BindView(R.id.txt_rating)
        TextView rating;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            Glide.with(itemView.getContext())
                    .load(movie.getPhoto())
                    .into(photo);
            title.setText(movie.getTitle());
            year.setText(movie.getReleaseDate());

            String[] genreName = (movie.getGenre()).split(",");
            genre.setText(genreName[0]);

            rating.setText(movie.getRating());
        }
    }
}
