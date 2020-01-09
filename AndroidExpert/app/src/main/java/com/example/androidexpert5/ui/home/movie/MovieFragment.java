package com.example.androidexpert5.ui.home.movie;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.example.androidexpert5.DetailActivity;
import com.example.androidexpert5.R;
import com.example.androidexpert5.adapter.MovieAdapter;
import com.example.androidexpert5.model.Movie;
import com.example.androidexpert5.ui.MovieViewModel;
import com.example.androidexpert5.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    private MovieViewModel movieViewModel;
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

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            showResultData(false, "");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            HomeFragment.searchView.onActionViewCollapsed();


            HomeFragment.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    showResultData(true, query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

            HomeFragment.searchView.setOnCloseListener(() -> {
                showResultData(false, "");
                return false;
            });
        }
    }

    private void init() {
        AndroidNetworking.initialize(Objects.requireNonNull(getActivity()));
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        ButterKnife.bind(this, getActivity());
        adapter = new MovieAdapter();
    }

    private void showResultData(Boolean isSearching, String query) {
        init();

        if (movieViewModel != null && movieViewModel.getMovies().hasActiveObservers()) {
            movieViewModel.getMovies().removeObservers(this);
        }

        if (isSearching) {
            searchMovie(query);
        } else {
            defaultData();
        }

        movieViewModel.getMovies().observe(this, getMovie);

        showLoading(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(adapter);

        adapter.setOnItemClickCallBack(movie -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, movie);
            intent.putExtra(DetailActivity.DATA_TYPE, "list_home");
            startActivity(intent);
        });
    }

    private void defaultData() {
        movieViewModel.setMovie("movie");
    }

    private void searchMovie(String query) {
        movieViewModel.setMovieFilter("movie", query);

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
