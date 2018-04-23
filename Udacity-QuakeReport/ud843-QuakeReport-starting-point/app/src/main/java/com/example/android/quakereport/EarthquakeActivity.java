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
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {
    // Yol Haritası

    /**
     * ArrayList içerisine JSONParse işlemi sonucunda dönen değerleri ekledim
     * Eklenen her bir değer için view elemanı oluşturdum - şişirme işlemi yaptım.
     * Daha sonra adapter ile listview i birbirine bağladım.
     * Kullandığım adaptör ise ArrayAdaptör oldu.
     * Adaptör - Veriler ile görünümler arasında bağlantı sağlıyor.
     */
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Sahte Liste
        ArrayList<Model> depremListesi = Araclar.parseEdilenVeriler(); // Buradan zaten json veri dönecek ve yeni nesne oluşturulmuş olacak.


        // Listview ' i bağlama
        ListView depremListView = (ListView) findViewById(R.id.list);
        // final koymamızın sebebi tıklama olayını ele almak için.
        final Adapter ozelAdapter = new Adapter(this, depremListesi);


        // Adapter ' e listview ' i bağlıyor.
        depremListView.setAdapter(ozelAdapter);

        depremListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Model ilgiliNesne = ozelAdapter.getItem(i);

                Uri ilgiliUri = Uri.parse(ilgiliNesne.getDepremUrl()); // Uri aracılığıyla URL ' e çevirdik.

                Intent intent = new Intent(Intent.ACTION_VIEW, ilgiliUri);

                startActivity(intent);


            }
        });


    }
}
