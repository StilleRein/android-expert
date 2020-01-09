package com.example.androidexpert2.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpert2.DetailActivity;
import com.example.androidexpert2.R;
import com.example.androidexpert2.adapter.MovieAdapter;
import com.example.androidexpert2.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    private ArrayList<Movie> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        rvMovie.setHasFixedSize(true);
        list.addAll(MovieData.getListData());
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvMovie.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallBack(movie -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, movie);
            startActivity(intent);
        });
    }
}