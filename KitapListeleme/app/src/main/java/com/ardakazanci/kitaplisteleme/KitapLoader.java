package com.ardakazanci.kitaplisteleme;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class KitapLoader extends AsyncTaskLoader<List<Kitap>> {

    public static final String LOG_TAG = KitapLoader.class.getSimpleName();

    private String mUrl;

    public KitapLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Kitap> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<Kitap> kitap = Utils.fetchKitapData(mUrl);
        return kitap;

    }
}
