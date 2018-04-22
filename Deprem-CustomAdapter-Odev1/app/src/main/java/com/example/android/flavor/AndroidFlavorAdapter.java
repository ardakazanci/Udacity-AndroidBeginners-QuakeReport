/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.flavor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
* Adapter : Her bir liste öğesi(AndroidFlavor nesnesi) için düzen sağlar.
* Bu düzen içerisinde AndroidFlavor nesnelerini içerir.
* Veri Kaynağı : AndroidFlavor
* Düzen Sağlayan : ArrayAdapter
* */
public class AndroidFlavorAdapter extends ArrayAdapter<AndroidFlavor> {

    private static final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();

    /**
     * Özel Adaptör için bir özel yapıcı metot yer almaktadır. Burada liste yi dolduracak veriler eşitlenir.
     *
     * @param context        Mevcut içerik düzen dosyasını şişirmek için kullanılır.
     * @param androidFlavors Listede görüntülenecek bir AndroidFlavor nesnesi , öğesi.
     */
    public AndroidFlavorAdapter(Activity context, ArrayList<AndroidFlavor> androidFlavors) {
        // Burada, ArrayAdapter'ün içerik ve liste için dahili depolamasını başlatıyoruz.
        // Özel bir tasarım için adaptör kullandığımızdan ikinci değer 0 kullandık herhangi bir sayıda olabilir.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position Listview'de , görüntülenmesi gereken veri listesindeki konum.
     * @param convertView Geridönüşüm için kullanılacak görünüm. Şişirilecek yani. Textview ImageView gibi. Elemanlar üstüne eklenecek Tekrar kullanılacak
     * @param parent Şişirilen elemanların (XML Kodunda yer view elemanlarının ) tümü.
     * @return İlgili position a ait görünüm döndürülecek. 1.Eleman ve görünümü 2.Eleman ve görünümü gibi.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Mevcut görünüm yeniden kullanılması kontrol ediliyor. Yoksa şişiriliyor.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Listede AndroidFlavor içerisinde yer alan ilgili nesnesi alıyoruz.
        AndroidFlavor currentAndroidFlavor = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        // İlgili nesnenin get metodu ile birlikte değeri alıp textview ' e aktarıyoruz diğerleride aynı şekilde
        nameTextView.setText(currentAndroidFlavor.getVersionName());


        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);

        numberTextView.setText(currentAndroidFlavor.getVersionNumber());


        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());


        return listItemView;
    }

}
