package com.example.androidexpert4.ui.favorite.movie;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpert4.DetailActivity;
import com.example.androidexpert4.MainActivity;
import com.example.androidexpert4.R;
import com.example.androidexpert4.adapter.FavoriteAdapter;
import com.example.androidexpert4.model.FavoriteMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieFragment extends Fragment {

    @BindView(R.id.rv_fav_movie)
    RecyclerView rvFavMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ButterKnife.bind(this, getActivity());
            rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            getFavMovie();
        }
    }

    private void getFavMovie() {
        List<FavoriteMovie> favoriteMovieList = MainActivity.databaseFavorite.daoFavoriteMovie().getFavoriteData("movie");
        FavoriteAdapter adapter = new FavoriteAdapter(favoriteMovieList);
        rvFavMovie.setAdapter(adapter);

        adapter.setOnItemClickCallBack(favoriteMovie -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, favoriteMovie);
            intent.putExtra(DetailActivity.DATA_TYPE, "list_favorite");
            startActivity(intent);
        });
    }
}
