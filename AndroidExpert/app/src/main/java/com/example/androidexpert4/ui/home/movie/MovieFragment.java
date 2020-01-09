package com.example.androidexpert4.ui.home.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.example.androidexpert4.DetailActivity;
import com.example.androidexpert4.R;
import com.example.androidexpert4.adapter.MovieAdapter;
import com.example.androidexpert4.model.Movie;
import com.example.androidexpert4.ui.MovieViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    private MovieAdapter adapter;
    private final Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setData(movies);
                adapter.notifyDataSetChanged();
                showLoading(false);
            }
        }
    };
    private MovieViewModel movieViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            init();
            prepareData();

            rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovie.setAdapter(adapter);

            adapter.setOnItemClickCallBack(movie -> {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, movie);
                intent.putExtra(DetailActivity.DATA_TYPE, "list_home");
                startActivity(intent);
            });
        }
    }

    private void init() {
        AndroidNetworking.initialize(getActivity());
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);
        ButterKnife.bind(this, getActivity());
        adapter = new MovieAdapter();
    }

    private void prepareData() {
        movieViewModel.setMovie("movie");
        showLoading(true);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
