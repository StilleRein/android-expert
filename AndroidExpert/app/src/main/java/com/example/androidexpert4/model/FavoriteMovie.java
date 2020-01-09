package com.example.androidexpert4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoriteMovies")
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
    @PrimaryKey
    private int id;
    private String photo;
    private String title;
    private String releaseDate;
    private String genre;
    private String rating;
    private String description;
    private String duration;
    private String banner;
    private String type;

    public FavoriteMovie() {

    }

    protected FavoriteMovie(Parcel in) {
        id = in.readInt();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
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
