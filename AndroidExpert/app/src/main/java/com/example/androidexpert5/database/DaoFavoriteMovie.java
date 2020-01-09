package com.example.androidexpert5.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidexpert5.model.FavoriteMovie;

import java.util.List;

@Dao
public interface DaoFavoriteMovie {
    @Insert
    void addData(FavoriteMovie favoriteMovie);

    @Query("select * from favoriteMovies where " + FavoriteMovie.COLUMN_TYPE + "=:type")
    List<FavoriteMovie> getFavoriteData(String type);

    @Query("select * from favoriteMovies")
    List<FavoriteMovie> getAllFavoriteData();

    @Query("select exists (select 1 from favoriteMovies where " + FavoriteMovie.COLUMN_ID + "=:id)")
    int isFavorite(long id);

    @Query("select * from favoriteMovies where " + FavoriteMovie.COLUMN_ID + "=:id")
    Cursor selectById(long id);

    @Query("select * from favoriteMovies")
    Cursor selectAll();

    @Delete
    void delete(FavoriteMovie favoriteMovie);

    @Query("delete from favoriteMovies where " + FavoriteMovie.COLUMN_ID + "=:id")
    int deleteById(long id);

    @Query("delete from favoriteMovies")
    void deleteAll();

    @Update
    int update(FavoriteMovie favoriteMovie);

    @Insert
    long insert(FavoriteMovie favoriteMovie);
}
