package com.example.androidexpert5.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidexpert5.MainActivity;
import com.example.androidexpert5.R;
import com.example.androidexpert5.model.FavoriteMovie;

import java.util.List;
import java.util.concurrent.ExecutionException;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context myContext;
    private final List<FavoriteMovie> favoriteMovieList = MainActivity.databaseFavorite.daoFavoriteMovie().getAllFavoriteData();

    StackRemoteViewsFactory(Context context) {
        myContext = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return favoriteMovieList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(myContext.getPackageName(), R.layout.widget_item);
        Bitmap photo = null;
        try {
            photo = Glide.with(myContext)
                    .asBitmap()
                    .load(favoriteMovieList.get(position).getPhoto())
                    .apply(new RequestOptions().fitCenter())
                    .submit()
                    .get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.imageView, photo);
        Bundle extras = new Bundle();
        extras.putInt(FavoriteStackWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
