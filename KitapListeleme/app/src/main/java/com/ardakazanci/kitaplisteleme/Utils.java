package com.ardakazanci.kitaplisteleme;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

// Final denmesinin sebebi bu sınıfa , sınıf adıyla erişebileceğiz.
public final class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();

    // Boş yapıcı metot private olmasının sebebi diğer şartlarda erişelemesi için.
    private Utils() {
    }

    // ANA METOT
    public static List<Kitap> fetchKitapData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "ANA İŞLEM METODUNDA HATA VAR" + e);

        }

        List<Kitap> olusturulanKitaplar = extractFeatureFromJson(jsonResponse);
        return olusturulanKitaplar;

    }

    // URL oluşturuyor
    private static URL createUrl(String stringUrl) {

        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, " - URL Oluşturma Hatası - Metot : Create URL");
        }

        return url;

    }

    // Oluşturulan URL' e istek atılıyor ve JSON Çıktısı byte halde InputStream'e atanıyor
    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "- HTTP İstek Hatası" + e);


        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    // InputStream'de yer alan parçalanmış byte veriler satır satır okunup , anlaşılır hale getiriliyor.
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    // JSON Parse işelmi ve kitap nesnesi ouşturma
    private static List<Kitap> extractFeatureFromJson(String kitapJson) {
        // Eğer boş JSON veri geldiyse boş döndür.
        if (TextUtils.isEmpty(kitapJson)) {
            return null;
        }

        List<Kitap> kitaplar = new ArrayList<>(); // Parse edilen veriler burada nesne olarak tutulacak;

        try {

            JSONObject root = new JSONObject(kitapJson);
            JSONArray kitapItems = root.getJSONArray("items");
            for (int i = 0; i < kitapItems.length(); i++) {

                JSONObject kitapBilgiArray = kitapItems.getJSONObject(i); // 0 ' ın içinde ki
                JSONObject kitapBilgi = kitapBilgiArray.getJSONObject("volumeInfo");
                String kitapBaslik = kitapBilgi.getString("title");
                String kitapAciklama = kitapBilgi.getString("description");
                String kitapUrl = kitapBilgi.getString("previewLink");
                kitapAciklama = metinKisalt(kitapAciklama).concat("...");
                Kitap kitap = new Kitap(kitapBaslik, kitapAciklama, kitapUrl);
                kitaplar.add(kitap);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "JSON PARSE İŞLEMİNDE HATA " + e);


        }

        return kitaplar;

    }


    private static String metinKisalt(String metin) {

        metin = metin.substring(0, 50);
        return metin;

    }

}
