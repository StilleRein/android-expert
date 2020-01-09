package com.example.androidexpert5.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidexpert5.MainActivity;
import com.example.androidexpert5.model.FavoriteMovie;

import java.util.ArrayList;

@Database(entities = {FavoriteMovie.class}, version = 1, exportSchema = false)
public abstract class DatabaseFavorite extends RoomDatabase {
    private static DatabaseFavorite dbInstance;

    public static synchronized DatabaseFavorite getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room
                    .databaseBuilder(context.getApplicationContext(), DatabaseFavorite.class, "ex")
                    .build();
            dbInstance.populateInitialData();
        }
        return dbInstance;
    }

    public abstract DaoFavoriteMovie daoFavoriteMovie();

    private void populateInitialData() {

        ArrayList<FavoriteMovie> favoriteMovieList = new ArrayList<>(MainActivity.databaseFavorite.daoFavoriteMovie().getAllFavoriteData());

        daoFavoriteMovie().deleteAll();

        FavoriteMovie favoriteMovie = new FavoriteMovie();

        for (int i = 0; i < favoriteMovieList.size(); i++) {
            favoriteMovie.setPhoto(favoriteMovieList.get(i).getPhoto());
            favoriteMovie.setTitle(favoriteMovieList.get(i).getTitle());
            favoriteMovie.setReleaseDate(favoriteMovieList.get(i).getReleaseDate());
            favoriteMovie.setGenre(favoriteMovieList.get(i).getGenre());
            favoriteMovie.setRating(favoriteMovieList.get(i).getRating());

            daoFavoriteMovie().insert(favoriteMovie);
        }
    }
}
