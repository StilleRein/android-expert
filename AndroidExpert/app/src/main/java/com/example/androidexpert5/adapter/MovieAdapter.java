package com.example.androidexpert5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidexpert5.MainActivity;
import com.example.androidexpert5.R;
import com.example.androidexpert5.model.FavoriteMovie;
import com.example.androidexpert5.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final ArrayList<Movie> listMovies = new ArrayList<>();
    private OnItemClickCallBack onItemClickCallBack;
    private Context context;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public void setData(ArrayList<Movie> items) {
        listMovies.clear();
        listMovies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view, parent, false);
        context = parent.getContext();
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bind(listMovies.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickCallBack.onItemClicked(listMovies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
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
        @BindView(R.id.btn_fav)
        Button btnFav;

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

            if (MainActivity.databaseFavorite.daoFavoriteMovie().isFavorite(movie.getId()) == 1) {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_24dp);
            } else {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
            }

            btnFav.setOnClickListener(v -> {
                FavoriteMovie favoriteMovie = new FavoriteMovie();
                favoriteMovie.setId(movie.getId());
                favoriteMovie.setTitle(movie.getTitle());
                favoriteMovie.setBanner(movie.getBanner());
                favoriteMovie.setDescription(movie.getDescription());
                favoriteMovie.setDuration(movie.getDuration());
                favoriteMovie.setGenre(movie.getGenre());
                favoriteMovie.setPhoto(movie.getPhoto());
                favoriteMovie.setRating(movie.getRating());
                favoriteMovie.setReleaseDate(movie.getReleaseDate());
                favoriteMovie.setType(movie.getType());

                if (MainActivity.databaseFavorite.daoFavoriteMovie().isFavorite(movie.getId()) != 1) {
                    btnFav.setBackgroundResource(R.drawable.ic_favorite_24dp);
                    MainActivity.databaseFavorite.daoFavoriteMovie().addData(favoriteMovie);
                    Toast.makeText(context, R.string.add_favorite, Toast.LENGTH_SHORT).show();
                } else {
                    btnFav.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
                    MainActivity.databaseFavorite.daoFavoriteMovie().delete(favoriteMovie);
                    Toast.makeText(context, R.string.remove_favorite, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
