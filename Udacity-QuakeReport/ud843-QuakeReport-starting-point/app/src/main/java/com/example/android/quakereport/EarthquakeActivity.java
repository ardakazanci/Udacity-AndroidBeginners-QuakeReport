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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Sahte Liste
        ArrayList<Model> depremListesi = new ArrayList<Model>();
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));
        depremListesi.add(new Model("7.2", "Ankara", "Çarşamba 2016"));


        // Listview ' i bağlama
        ListView depremListView = (ListView) findViewById(R.id.list);

        Adapter ozelAdapter = new Adapter(this, depremListesi);


        // Adapter ' e listview ' i bağlıyor.
        depremListView.setAdapter(ozelAdapter);
    }
}
