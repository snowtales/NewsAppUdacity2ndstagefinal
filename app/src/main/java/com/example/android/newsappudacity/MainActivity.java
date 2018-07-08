package com.example.android.newsappudacity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsClass>> {
    private static final int NEWS_LOADER_ID = 1;
    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?show-tags=contributor&q='snowboarding'&api-key=9ac27e16-ff04-4553-ae14-bf5fc2eeed79";
    private TextView mEmptyView;
    private ProgressBar mProgressBar;
    private AdapterListView newsAdapter;

    @Override
    public Loader<List<NewsClass>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsClass>> loader, List<NewsClass> newsGuardian) {
        // Clear the adapter of previous earthquake data
        newsAdapter.clear();
        mEmptyView.setText(R.string.no_news);
        mProgressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsGuardian != null && !newsGuardian.isEmpty()) {
            newsAdapter.addAll(newsGuardian);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsClass>> loader) {
        // Loader reset, so we can clear out our existing data.
        newsAdapter.clear();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsListView = findViewById(R.id.list);
        mEmptyView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyView);
        mProgressBar = findViewById(R.id.circle);
        newsAdapter = new AdapterListView(this, new ArrayList<NewsClass>());

        newsListView.setAdapter(newsAdapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsClass currentNews = newsAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            ;
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            mProgressBar = findViewById(R.id.circle);
            mProgressBar.setVisibility(View.GONE);
            mEmptyView.setText(R.string.no_internet);
        }
    }
}
