package com.example.favoriteapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoriteapplication.R;
import com.example.favoriteapplication.model.FavoriteMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private Cursor myCursor;
    private Context context;

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view, parent, false);
        context = parent.getContext();
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        if (myCursor.moveToPosition(position)) {
            Glide.with(context)
                    .load(myCursor.getString(myCursor.getColumnIndexOrThrow(FavoriteMovie.COLUMN_PHOTO)))
                    .into(holder.photo);

            holder.title.setText(myCursor.getString(
                    myCursor.getColumnIndexOrThrow(FavoriteMovie.COLUMN_TITLE)
            ));

            holder.year.setText(myCursor.getString(
                    myCursor.getColumnIndexOrThrow(FavoriteMovie.COLUMN_RELEASE_DATE)
            ));

            String[] genreName = (myCursor.getString(myCursor.getColumnIndexOrThrow(FavoriteMovie.COLUMN_GENRE))).split(",");
            holder.genre.setText(genreName[0]);

            holder.rating.setText(myCursor.getString(
                    myCursor.getColumnIndexOrThrow(FavoriteMovie.COLUMN_RATING)
            ));

            holder.btnFav.setBackgroundResource(R.drawable.ic_favorite_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return myCursor == null ? 0 : myCursor.getCount();
    }

    public void setMovies(Cursor cursor) {
        myCursor = cursor;
        notifyDataSetChanged();
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
    }
}
