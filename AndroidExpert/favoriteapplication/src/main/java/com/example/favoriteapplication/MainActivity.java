package com.example.favoriteapplication;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favoriteapplication.adapter.FavoriteAdapter;
import com.example.favoriteapplication.model.FavoriteMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String AUTHORITY = "com.example.androidexpert5.provider";
    private static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/favoriteMovies");
    private static final int LOADER_MOVIES = 1;
    @BindView(R.id.rv_fav_movie)
    RecyclerView rvFavMovie;
    private FavoriteAdapter adapter;
    private final LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            if (id == LOADER_MOVIES) {
                return new CursorLoader(getApplicationContext(),
                        URI_MOVIE,
                        new String[]{
                                FavoriteMovie.COLUMN_PHOTO,
                                FavoriteMovie.COLUMN_TITLE,
                                FavoriteMovie.COLUMN_RELEASE_DATE,
                                FavoriteMovie.COLUMN_GENRE,
                                FavoriteMovie.COLUMN_RATING
                        },
                        null, null, null);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            if (loader.getId() == LOADER_MOVIES) {
                adapter.setMovies(data);
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            if (loader.getId() == LOADER_MOVIES) {
                adapter.setMovies(null);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        rvFavMovie.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        adapter = new FavoriteAdapter();
        rvFavMovie.setAdapter(adapter);
        LoaderManager.getInstance(this).initLoader(LOADER_MOVIES, null, loaderCallbacks);
    }
}
