package com.example.androidexpert4.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidexpert4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreAdapter extends ArrayAdapter<String> {

    private final String[] genreMovie;
    private final Activity context;

    public GenreAdapter(Activity context, String[] genreMovie) {
        super(context, R.layout.genre_list_view, genreMovie);

        this.context = context;
        this.genreMovie = genreMovie;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();

        } else {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.genre_list_view, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.genre.setText(genreMovie[position]);

        return view;
    }

    class ViewHolder {
        @BindView(R.id.txt_genre)
        TextView genre;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
