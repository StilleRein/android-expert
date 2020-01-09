package com.example.favoriteapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FavoriteMovie.TABLE_NAME)
public class FavoriteMovie implements Parcelable {
    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    private static final String COLUMN_ID = "id";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RELEASE_DATE = "releaseDate";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_RATING = "rating";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_BANNER = "banner";
    private static final String COLUMN_TYPE = "type";
    static final String TABLE_NAME = "favoriteMovies";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    private final long id;
    @ColumnInfo(name = COLUMN_PHOTO)
    private final String photo;
    @ColumnInfo(name = COLUMN_TITLE)
    private final String title;
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    private final String releaseDate;
    @ColumnInfo(name = COLUMN_GENRE)
    private final String genre;
    @ColumnInfo(name = COLUMN_RATING)
    private final String rating;
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private final String description;
    @ColumnInfo(name = COLUMN_DURATION)
    private final String duration;
    @ColumnInfo(name = COLUMN_BANNER)
    private final String banner;
    @ColumnInfo(name = COLUMN_TYPE)
    private final String type;

    private FavoriteMovie(Parcel in) {
        id = in.readLong();
        photo = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        genre = in.readString();
        rating = in.readString();
        description = in.readString();
        duration = in.readString();
        banner = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(photo);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(genre);
        dest.writeString(rating);
        dest.writeString(description);
        dest.writeString(duration);
        dest.writeString(banner);
        dest.writeString(type);
    }
}

