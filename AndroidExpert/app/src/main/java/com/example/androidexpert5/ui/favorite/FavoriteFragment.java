package com.example.androidexpert5.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.androidexpert5.R;
import com.example.androidexpert5.adapter.ViewPagerAdapter;
import com.example.androidexpert5.ui.favorite.movie.FavoriteMovieFragment;
import com.example.androidexpert5.ui.favorite.tvshow.FavoriteTvShowFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment {

    @BindView(R.id.tabLayout2)
    TabLayout tabLayout;
    @BindView(R.id.viewPager2)
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            ButterKnife.bind(this, getActivity());

            ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

            adapter.addFragment(new FavoriteMovieFragment(), getString(R.string.title_movie));
            adapter.addFragment(new FavoriteTvShowFragment(), getString(R.string.title_tv_show));

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}