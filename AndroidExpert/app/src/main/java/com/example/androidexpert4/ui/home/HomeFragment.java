package com.example.androidexpert4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.androidexpert4.R;
import com.example.androidexpert4.adapter.ViewPagerAdapter;
import com.example.androidexpert4.ui.home.movie.MovieFragment;
import com.example.androidexpert4.ui.home.tvshow.TvShowFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            ButterKnife.bind(this, getActivity());

            ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

            adapter.addFragment(new MovieFragment(), getString(R.string.title_movie));
            adapter.addFragment(new TvShowFragment(), getString(R.string.title_tv_show));

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}