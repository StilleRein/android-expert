package com.example.androidexpert4.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.androidexpert4.BuildConfig;
import com.example.androidexpert4.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private final ArrayList<String> listGenres = new ArrayList<>();
    private final MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
    private int count = 0;

    public void setMovie(String typeName) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();

        String url = "http://api.themoviedb.org/3/discover/" + typeName + "?api_key=" + BuildConfig.API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);

                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        int movieId = movie.getInt("id");
                        String detailUrl = "https://api.themoviedb.org/3/" + typeName + "/" + movieId + "?api_key=" + BuildConfig.API_KEY + "&language=en-US";

                        AndroidNetworking.get(detailUrl)
                                .setPriority(Priority.MEDIUM)
                                .setTag("details")
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONArray genreList = response.getJSONArray("genres");
                                            listGenres.clear();
                                            for (int i = 0; i < genreList.length(); i++) {
                                                JSONObject movieGenre = genreList.getJSONObject(i);
                                                listGenres.add(movieGenre.getString("name"));
                                            }
                                            Movie movies = new Movie(movie, typeName, response, listGenres);
                                            listItems.add(movies);

                                            if (count == list.length() - 1) {
                                                listMovies.postValue(listItems);
                                            }
                                            count++;

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Log.d("onError", anError + "");
                                    }
                                });
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
}
