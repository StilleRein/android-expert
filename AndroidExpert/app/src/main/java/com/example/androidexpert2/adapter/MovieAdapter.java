package com.example.androidexpert2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidexpert2.R;
import com.example.androidexpert2.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie;

    private OnItemClickCallBack onItemClickCallBack;

    public MovieAdapter(ArrayList<Movie> list) {
        this.listMovie = list;
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getPhoto())
                .into(holder.photo);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getReleaseYear());

        String[] genreSplit = movie.getGenre().split(",");
        holder.genre.setText(genreSplit[0]);

        holder.rating.setText(movie.getRating());

        holder.itemView.setOnClickListener(
                view -> onItemClickCallBack.onItemClicked(
                        listMovie.get(holder.getAdapterPosition())
                )
        );
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Movie movie);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
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

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
