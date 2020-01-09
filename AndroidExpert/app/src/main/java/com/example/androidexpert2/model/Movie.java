package com.example.androidexpert2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

    private String photo;
    private String title;
    private String releaseYear;
    private String genre;
    private String rating;
    private String description;
    private String duration;
    private String banner;

    public Movie() {
    }

    private Movie(Parcel in) {
        this.photo = in.readString();
        this.title = in.readString();
        this.releaseYear = in.readString();
        this.genre = in.readString();
        this.rating = in.readString();
        this.description = in.readString();
        this.duration = in.readString();
        this.banner = in.readString();
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
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
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.releaseYear);
        dest.writeString(this.genre);
        dest.writeString(this.rating);
        dest.writeString(this.description);
        dest.writeString(this.duration);
        dest.writeString(this.banner);
    }
}
