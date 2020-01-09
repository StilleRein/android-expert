package com.example.androidexpert4.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidexpert4.model.FavoriteMovie;

@Database(entities = {FavoriteMovie.class}, version = 1, exportSchema = false)
public abstract class DatabaseFavorite extends RoomDatabase {
    public abstract DaoFavoriteMovie daoFavoriteMovie();
}
