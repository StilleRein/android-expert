package com.example.androidexpert2.ui.tvshow;

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

public class TvShowFragment extends Fragment {

    @BindView(R.id.rv_tv_show)
    RecyclerView rvTvShow;
    private ArrayList<Movie> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        ButterKnife.bind(this, view);

        rvTvShow.setHasFixedSize(true);
        list.addAll(TvShowData.getListData());
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter tvShowAdapter = new MovieAdapter(list);
        rvTvShow.setAdapter(tvShowAdapter);

        tvShowAdapter.setOnItemClickCallBack(tvShow -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, tvShow);
            startActivity(intent);
        });
    }
}