package com.example.androidexpert5.ui.favorite.tvshow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpert5.DetailActivity;
import com.example.androidexpert5.MainActivity;
import com.example.androidexpert5.R;
import com.example.androidexpert5.adapter.FavoriteAdapter;
import com.example.androidexpert5.model.FavoriteMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTvShowFragment extends Fragment {

    @BindView(R.id.rv_fav_tv_show)
    RecyclerView rvFavTvShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ButterKnife.bind(this, getActivity());
            rvFavTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
            getFavTvShow();
        }
    }

    private void getFavTvShow() {
        List<FavoriteMovie> favoriteMovieList = MainActivity.databaseFavorite.daoFavoriteMovie().getFavoriteData("tv");
        FavoriteAdapter adapter = new FavoriteAdapter(favoriteMovieList);
        rvFavTvShow.setAdapter(adapter);

        adapter.setOnItemClickCallBack(favoriteMovie -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, favoriteMovie);
            intent.putExtra(DetailActivity.DATA_TYPE, "list_favorite");
            startActivity(intent);
        });
    }
}
