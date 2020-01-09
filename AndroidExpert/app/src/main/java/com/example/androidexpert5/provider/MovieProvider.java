package com.example.androidexpert5.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.androidexpert5.database.DaoFavoriteMovie;
import com.example.androidexpert5.database.DatabaseFavorite;
import com.example.androidexpert5.model.FavoriteMovie;

public class MovieProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.androidexpert5.provider";

    private static final int CODE_FAVORITE_MOVIE_DIR = 1;
    private static final int CODE_FAVORITE_MOVIE_ITEM = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, FavoriteMovie.TABLE_NAME, CODE_FAVORITE_MOVIE_DIR);
        MATCHER.addURI(AUTHORITY, FavoriteMovie.TABLE_NAME + "/*", CODE_FAVORITE_MOVIE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int code = MATCHER.match(uri);

        if (code == CODE_FAVORITE_MOVIE_DIR || code == CODE_FAVORITE_MOVIE_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            DaoFavoriteMovie daoFavoriteMovie = DatabaseFavorite.getInstance(context).daoFavoriteMovie();
            final Cursor cursor;
            if (code == CODE_FAVORITE_MOVIE_DIR) {
                cursor = daoFavoriteMovie.selectAll();
            } else {
                cursor = daoFavoriteMovie.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_FAVORITE_MOVIE_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + FavoriteMovie.TABLE_NAME;
            case CODE_FAVORITE_MOVIE_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + FavoriteMovie.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        switch (MATCHER.match(uri)) {
            case CODE_FAVORITE_MOVIE_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                final long id = DatabaseFavorite.getInstance(context).daoFavoriteMovie()
                        .insert(FavoriteMovie.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_FAVORITE_MOVIE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_FAVORITE_MOVIE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_FAVORITE_MOVIE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final FavoriteMovie favoriteMovie = FavoriteMovie.fromContentValues(values);
                favoriteMovie.setId(ContentUris.parseId(uri));
                final int count = DatabaseFavorite.getInstance(context).daoFavoriteMovie()
                        .update(favoriteMovie);
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_FAVORITE_MOVIE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_FAVORITE_MOVIE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final int count = DatabaseFavorite.getInstance(context).daoFavoriteMovie()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
