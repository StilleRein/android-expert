package com.example.androidexpert5.model;

import android.content.ContentValues;
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

    public static final String TABLE_NAME = "favoriteMovies";

    public static final String COLUMN_ID = "id";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_RELEASE_DATE = "releaseDate";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_BANNER = "banner";
    public static final String COLUMN_TYPE = "type";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    private long id;
    @ColumnInfo(name = COLUMN_PHOTO)
    private String photo;
    @ColumnInfo(name = COLUMN_TITLE)
    private String title;
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    private String releaseDate;
    @ColumnInfo(name = COLUMN_GENRE)
    private String genre;
    @ColumnInfo(name = COLUMN_RATING)
    private String rating;
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private String description;
    @ColumnInfo(name = COLUMN_DURATION)
    private String duration;
    @ColumnInfo(name = COLUMN_BANNER)
    private String banner;
    @ColumnInfo(name = COLUMN_TYPE)
    private String type;

    protected FavoriteMovie(Parcel in) {
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

    public FavoriteMovie() {

    }

    public static FavoriteMovie fromContentValues(ContentValues values) {
        final FavoriteMovie favoriteMovie = new FavoriteMovie();

        if (values.containsKey(COLUMN_ID)) {
            favoriteMovie.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_PHOTO)) {
            favoriteMovie.photo = values.getAsString(COLUMN_PHOTO);
        }
        if (values.containsKey(COLUMN_TITLE)) {
            favoriteMovie.title = values.getAsString(COLUMN_TITLE);
        }
        if (values.containsKey(COLUMN_GENRE)) {
            favoriteMovie.genre = values.getAsString(COLUMN_GENRE);
        }
        if (values.containsKey(COLUMN_RATING)) {
            favoriteMovie.rating = values.getAsString(COLUMN_RATING);
        }
        if (values.containsKey(COLUMN_DESCRIPTION)) {
            favoriteMovie.description = values.getAsString(COLUMN_DESCRIPTION);
        }
        if (values.containsKey(COLUMN_DURATION)) {
            favoriteMovie.duration = values.getAsString(COLUMN_DURATION);
        }
        if (values.containsKey(COLUMN_BANNER)) {
            favoriteMovie.banner = values.getAsString(COLUMN_BANNER);
        }
        if (values.containsKey(COLUMN_TYPE)) {
            favoriteMovie.type = values.getAsString(COLUMN_TYPE);
        }
        if (values.containsKey(COLUMN_PHOTO)) {
            favoriteMovie.photo = values.getAsString(COLUMN_PHOTO);
        }

        return favoriteMovie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
