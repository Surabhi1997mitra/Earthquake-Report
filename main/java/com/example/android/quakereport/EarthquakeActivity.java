
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.AsyncTaskLoader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeDetail>>
{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private EarthquakeDetailAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mAdapter = new EarthquakeDetailAdapter(this, new ArrayList<EarthquakeDetail>());

        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setEmptyView(mEmptyStateTextView);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthquakeDetail currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent webintent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(webintent);
            }
        });
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this);
            Log.i(LOG_TAG, "TEST");
        }
        else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<EarthquakeDetail>> onCreateLoader(int id, Bundle args)
    {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeDetail>> loader, List<EarthquakeDetail> data)
    {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null ) {
            mEmptyStateTextView.setText(R.string.no_internet);
        } else if (networkInfo!=null && networkInfo.isConnected()){
            mEmptyStateTextView.setText(R.string.empty_list);
        }

        mEmptyStateTextView.setText(R.string.no_earthquakes);
        mAdapter.clear();
        if(data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeDetail>> loader)
    {
        mAdapter.clear();
    }
}

