package com.example.androidexpert5;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.androidexpert5.adapter.GenreAdapter;
import com.example.androidexpert5.model.FavoriteMovie;
import com.example.androidexpert5.model.Movie;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";
    public static final String DATA_TYPE = "data_type";
    @BindView(R.id.img_photo)
    ImageView photo;
    @BindView(R.id.txt_title)
    TextView title;
    @BindView(R.id.txt_date)
    TextView date;
    @BindView(R.id.txt_rating)
    TextView rating;
    @BindView(R.id.txt_description)
    TextView description;
    @BindView(R.id.txt_duration)
    TextView duration;
    @BindView(R.id.img_banner)
    ImageView banner;
    @BindView(R.id.gv_genre)
    GridView gridView;
    @BindView(R.id.txt_synopsis)
    TextView synopsis;
    @BindView(R.id.txt_time)
    TextView time;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        synopsis.setText(getResources().getString(R.string.synopsis));
        time.setText(getResources().getString(R.string.time));

        loadData();
        showLoading(false);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        Objects.requireNonNull(toolbar.getNavigationIcon()).setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
    }

    private void loadData() {
        showLoading(true);

        String type = getIntent().getStringExtra(DATA_TYPE);

        String[] genreMovie;
        if (type.equals("list_home")) {
            Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);

            title.setText(movie.getTitle());
            date.setText(movie.getReleaseDate());
            rating.setText(movie.getRating());
            description.setText(movie.getDescription());
            duration.setText(movie.getDuration());
            Glide.with(getBaseContext())
                    .load(movie.getPhoto())
                    .into(photo);
            Glide.with(getBaseContext())
                    .load(movie.getBanner())
                    .into(banner);

            genreMovie = (movie.getGenre()).split(",");

            GenreAdapter genreAdapter = new GenreAdapter(this, genreMovie);
            gridView.setAdapter(genreAdapter);
        } else {
            FavoriteMovie favoriteMovie = getIntent().getParcelableExtra(EXTRA_DATA);

            title.setText(favoriteMovie.getTitle());
            date.setText(favoriteMovie.getReleaseDate());
            rating.setText(favoriteMovie.getRating());
            description.setText(favoriteMovie.getDescription());
            duration.setText(favoriteMovie.getDuration());
            Glide.with(getBaseContext())
                    .load(favoriteMovie.getPhoto())
                    .into(photo);
            Glide.with(getBaseContext())
                    .load(favoriteMovie.getBanner())
                    .into(banner);

            genreMovie = (favoriteMovie.getGenre()).split(",");

            GenreAdapter genreAdapter = new GenreAdapter(this, genreMovie);
            gridView.setAdapter(genreAdapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
