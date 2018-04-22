package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Model> {

    private static final String LOG_TAG = Adapter.class.getSimpleName();


    public Adapter(Activity context, ArrayList<Model> deprem) {

        super(context, 0, deprem);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listeItemElemani = convertView;

        if (listeItemElemani == null) {

            listeItemElemani = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);


        }

        Model depremNesnesi = getItem(position); // İlgili pozisyonda ki deprem nesnesi alıdı. Örneğin 1.inci deprem

        TextView depremSiddet = (TextView) listeItemElemani.findViewById(R.id.depremSiddet);

        depremSiddet.setText(depremNesnesi.getDepremSiddeti());

        TextView depremKonum = (TextView) listeItemElemani.findViewById(R.id.depremKonum);

        depremKonum.setText(depremNesnesi.getDepremKonum());

        TextView depremZaman = (TextView) listeItemElemani.findViewById(R.id.depremZaman);

        depremZaman.setText(depremNesnesi.getDepremTarih());

        return listeItemElemani;


    }
}
