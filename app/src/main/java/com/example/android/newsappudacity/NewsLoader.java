package com.example.android.newsappudacity;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Анастасия on 08.07.2018.
 */

public class NewsLoader extends AsyncTaskLoader {
    private static final String LOG_TAG = NewsLoader.class.getName();

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsClass> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsClass> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
