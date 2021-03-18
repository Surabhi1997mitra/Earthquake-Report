package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeDetail>>
{
	private String mUrl;
	private static final String LOG_TAG = EarthquakeLoader.class.getName();
	public EarthquakeLoader(Context context,String url)
	{
		super(context);
		mUrl = url;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	public List<EarthquakeDetail> loadInBackground()
	{
		if(mUrl == null)
			return null;
		List<EarthquakeDetail> earthquakeDetails = QueryUtils.fetchEarthquakeData(mUrl);
		return earthquakeDetails;
	}
}
