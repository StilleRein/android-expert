package com.example.androidexpert4.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidexpert4.model.FavoriteMovie;

import java.util.List;

@Dao
public interface DaoFavoriteMovie {
    @Insert
    void addData(FavoriteMovie favoriteMovie);

    @Query("select * from favoriteMovies where type=:type")
    List<FavoriteMovie> getFavoriteData(String type);

    @Query("select exists (select 1 from favoriteMovies where id=:id)")
    int isFavorite(int id);

    @Delete
    void delete(FavoriteMovie favoriteMovie);
}
