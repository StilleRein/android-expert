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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final List<FavoriteMovie> favoriteMovieList;
    private OnItemClickCallBack onItemClickCallBack;
    private Context context;

    public FavoriteAdapter(List<FavoriteMovie> favoriteMovieList) {
        this.favoriteMovieList = favoriteMovieList;
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view, parent, false);
        context = parent.getContext();
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(favoriteMovieList.get(position));
        holder.itemView.setOnClickListener(v -> onItemClickCallBack.onItemClicked(favoriteMovieList.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList.size();
    }

    public interface OnItemClickCallBack {
        void onItemClicked(FavoriteMovie favoriteMovie);
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
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

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(FavoriteMovie favoriteMovie) {
            Glide.with(itemView.getContext())
                    .load(favoriteMovie.getPhoto())
                    .into(photo);
            title.setText(favoriteMovie.getTitle());
            year.setText(favoriteMovie.getReleaseDate());

            String[] genreName = (favoriteMovie.getGenre()).split(",");
            genre.setText(genreName[0]);

            rating.setText(favoriteMovie.getRating());

            if (MainActivity.databaseFavorite.daoFavoriteMovie().isFavorite(favoriteMovie.getId()) == 1) {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_24dp);
            } else {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
            }

            btnFav.setOnClickListener(v -> {
                FavoriteMovie favoriteMovieNew = new FavoriteMovie();
                favoriteMovieNew.setId(favoriteMovie.getId());
                favoriteMovieNew.setTitle(favoriteMovie.getTitle());
                favoriteMovieNew.setBanner(favoriteMovie.getBanner());
                favoriteMovieNew.setDescription(favoriteMovie.getDescription());
                favoriteMovieNew.setDuration(favoriteMovie.getDuration());
                favoriteMovieNew.setGenre(favoriteMovie.getGenre());
                favoriteMovieNew.setPhoto(favoriteMovie.getPhoto());
                favoriteMovieNew.setRating(favoriteMovie.getRating());
                favoriteMovieNew.setReleaseDate(favoriteMovie.getReleaseDate());
                favoriteMovieNew.setType(favoriteMovie.getType());

                if (MainActivity.databaseFavorite.daoFavoriteMovie().isFavorite(favoriteMovie.getId()) != 1) {
                    btnFav.setBackgroundResource(R.drawable.ic_favorite_24dp);
                    MainActivity.databaseFavorite.daoFavoriteMovie().addData(favoriteMovieNew);
                    Toast.makeText(context, R.string.add_favorite, Toast.LENGTH_SHORT).show();
                } else {
                    btnFav.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
                    MainActivity.databaseFavorite.daoFavoriteMovie().delete(favoriteMovieNew);
                    Toast.makeText(context, R.string.remove_favorite, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
