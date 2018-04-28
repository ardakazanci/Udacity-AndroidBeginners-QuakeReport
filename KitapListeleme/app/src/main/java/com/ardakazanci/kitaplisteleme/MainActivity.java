package com.ardakazanci.kitaplisteleme;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Kitap>> {

    private Button button;

    private EditText editText;

    private String editTextInput;


    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    private String searchQuery = REQUEST_URL;


    private KitapAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = findViewById(R.id.listView);

        adapter = new KitapAdapter(this, new ArrayList<Kitap>());

        list.setAdapter(adapter);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    // (FIXED) TO GET THE DATA IF THERE'S CONNECTION WITH INITIALIZING THE LOADER AND (RESTARTING IF LIST VIEW != NULL)

                    LoaderManager loaderManager = getSupportLoaderManager();
                    if (list == null) {
                        loaderManager.initLoader(0, null, MainActivity.this);
                    } else {
                        loaderManager.restartLoader(0, null, MainActivity.this);
                    }


                    editTextInput = editText.getText().toString();
                    searchQuery = REQUEST_URL + editTextInput + "&maxResults=10";
                    Log.v(LOG_TAG, searchQuery);

                    // TO RELOAD FOR THE SECOND OR THIRD SEARCH



            }

        });


    }

    @NonNull
    @Override
    public Loader<List<Kitap>> onCreateLoader(int id, @Nullable Bundle args) {


        return new KitapLoader(this, searchQuery);


    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Kitap>> loader, List<Kitap> data) {
        adapter.clear();

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Kitap>> loader) {
        adapter.clear();
    }
}
