package com.example.androidexpert3.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private int id;
    private String photo;
    private String title;
    private String releaseDate;
    private String genre;
    private String rating;
    private String description;
    private String duration;
    private String banner;

    public Movie(JSONObject object, String typeName, JSONObject response, ArrayList<String> movieGenre) {

        try {
            if (typeName.equals("movie")) {
                this.title = object.getString("title");
                this.releaseDate = object.getString("release_date");
                this.duration = response.getString("runtime");
            } else {
                this.title = object.getString("name");
                this.releaseDate = object.getString("first_air_date");

                String getDuration = response.getString("episode_run_time");

                this.duration = getDuration.substring(1, getDuration.length() - 1);
            }
            this.id = object.getInt("id");
            this.photo = "https://image.tmdb.org/t/p/w342" + object.getString("poster_path");

            this.genre = "";
            for (int i = 0; i < movieGenre.size(); i++) {
                this.genre += movieGenre.get(i) + ",";
            }

            this.rating = object.getString("vote_average");
            this.description = object.getString("overview");
            this.banner = "https://image.tmdb.org/t/p/w342" + object.getString("backdrop_path");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Movie(Parcel in) {
        id = in.readInt();
        photo = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        genre = in.readString();
        rating = in.readString();
        description = in.readString();
        duration = in.readString();
        banner = in.readString();
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
    }
}
