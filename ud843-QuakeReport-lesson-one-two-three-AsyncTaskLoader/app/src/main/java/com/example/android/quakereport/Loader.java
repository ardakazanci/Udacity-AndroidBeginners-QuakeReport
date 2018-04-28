package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class Loader extends AsyncTaskLoader<List<Earthquake>> {

    public static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";


    public Loader(Context context) {
        super(context);
    }

    @Override
    public List<Earthquake> loadInBackground() {

        List<Earthquake> depremler = new ArrayList<Earthquake>();

        String url = URL;

        depremler = QueryUtils.fetchEarthquakeData(url);

        return depremler;


    }


}
