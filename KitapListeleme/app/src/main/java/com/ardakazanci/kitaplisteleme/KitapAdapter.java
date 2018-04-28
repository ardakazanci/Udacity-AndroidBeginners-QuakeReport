package com.ardakazanci.kitaplisteleme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class KitapAdapter extends ArrayAdapter<Kitap> {

    // Custom Listview oluşturacağımızı super ile üst sınıfa belirtiyoruz.
    public KitapAdapter(Context context, List<Kitap> kitapList) {
        super(context, 0, kitapList);

    }


    // İlgili view elemanına ( nesneye göre view şişirme işlemi ) burada yapılacak
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView; // Bir görünüm alıyoruz - Bu görünüm aslında ilgili customListview için tasarlanan view
        if (listItemView == null) { // Eğer boş ise
            // gelen her veri ile bu view ' i doldur.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);

        }
        // İşlenecek nesneyi al.
        Kitap kitapNesnesi = getItem(position);

        TextView kitapBaslik = listItemView.findViewById(R.id.kitapBaslik);
        String kitapBaslikNesne = kitapNesnesi.getKitapBaslik();
        kitapBaslik.setText(kitapBaslikNesne);

        TextView kitapAciklama = listItemView.findViewById(R.id.kitapAciklama);
        String kitapAciklamaNesne = kitapNesnesi.getKitapAciklama();
        kitapAciklama.setText(kitapAciklamaNesne);

        return listItemView;


    }
}
