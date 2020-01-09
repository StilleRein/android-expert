package com.example.androidexpert;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidexpert.adapter.MovieAdapter;
import com.example.androidexpert.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movie_list)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TypedArray dataPhoto, dataBanner;
    private String[] dataTitle, dataYear, dataGenre, dataRating, dataDescription, dataDuration;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setReleaseYear(dataYear[i]);
            movie.setGenre(dataGenre[i]);
            movie.setRating(dataRating[i]);
            movie.setDescription(dataDescription[i]);
            movie.setDuration(dataDuration[i]);
            movie.setBanner(dataBanner.getResourceId(i, -1));
            movies.add(movie);
        }

        adapter.setMovies(movies);
    }

    private void prepare() {
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataYear = getResources().getStringArray(R.array.data_year);
        dataGenre = getResources().getStringArray(R.array.data_genre);
        dataRating = getResources().getStringArray(R.array.data_rating);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataDuration = getResources().getStringArray(R.array.data_duration);
        dataBanner = getResources().obtainTypedArray(R.array.data_banner);
    }
}
