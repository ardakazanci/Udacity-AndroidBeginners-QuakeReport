package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter extends ArrayAdapter<Model> {

    private static final String LOG_TAG = Adapter.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of "; // of ' tan öncesi ve sonrası olacak şekilde ayırma işlemi sağlayacağız.


    public Adapter(Activity context, ArrayList<Model> deprem) {
        // Özel bir tasarım olduğu için 0 değerini atadık.
        super(context, 0, deprem);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listeItemElemani = convertView;

        if (listeItemElemani == null) {

            listeItemElemani = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);


        }

        Model depremNesnesi = getItem(position); // İlgili pozisyonda ki deprem nesnesi alındı. Örneğin 1.inci deprem
        // İlgili değer üzerinden view şişirme işlemi başatıyoruz.

        // Aşağıda yapılacak işlemlerde ekstra işlemler için metotlar tanımlanmıştır. Örneğin zaman formatlama , resim değişmesi gibi.

        // Deprem Şiddeti Stillendirme
        TextView depremSiddet = (TextView) listeItemElemani.findViewById(R.id.depremSiddet);

        String formatDepremSiddet = formatDepremSiddeti(depremNesnesi.getDepremSiddeti());

        depremSiddet.setText(formatDepremSiddet);

        // Textview ' in bir gradientDrawable çizimi şekline kavuşması için.
        // İlgili textview bir dizayn - shape formatına bürünecek .
        GradientDrawable magnitudeDizayn = (GradientDrawable) depremSiddet.getBackground();

        int magnitudeColor = getMagnitudeColor(depremNesnesi.getDepremSiddeti());

        magnitudeDizayn.setColor(magnitudeColor);


        String tamKonum = depremNesnesi.getDepremKonum();
        String birinciKisim; // 74km NW of yada Near the
        String offset; // Ankara gibi

        if (tamKonum.contains(LOCATION_SEPARATOR)) {
            String[] bolumBir = tamKonum.split(LOCATION_SEPARATOR);
            offset = bolumBir[0] + LOCATION_SEPARATOR;
            birinciKisim = bolumBir[1];

        }else{
            offset = getContext().getString(R.string.near_the); // Mesafe
            birinciKisim = tamKonum;

        }

        TextView primaryLocationView = (TextView) listeItemElemani.findViewById(R.id.depremMesafe);
        primaryLocationView.setText(birinciKisim);

        TextView locationOffsetView = (TextView) listeItemElemani.findViewById(R.id.depremKonum);
        locationOffsetView.setText(offset);


        /**
         *
         * Tarih ve Zamanı Date Objesi olarak alıp metot aracılığıyla tarih ve saat olacak şekilde çevirme işlemi sağlıyoruz.
         *
         */
        Date dateObjesi = new Date(depremNesnesi.getDepremTarih()); // Date objesi oluşturuluyor. - Unix zamanı alıyor.

        TextView tarihView = (TextView) listeItemElemani.findViewById(R.id.depremTarih);
        String tarihFormatlama = formatTarih(dateObjesi);
        tarihView.setText(tarihFormatlama);


        TextView saatView = (TextView) listeItemElemani.findViewById(R.id.depremSaat);
        String saatFormatlama = formatSaat(dateObjesi);
        saatView.setText(saatFormatlama);


        return listeItemElemani;


    }


    // Adapter içerisinde verilerin görünümlerinin ayarlanma aşamasında bu işlemler uygunaldı.
    private String formatTarih(Date dateObjesi) {

        SimpleDateFormat tarihFormat = new SimpleDateFormat("LLL dd,YYYY");
        return tarihFormat.format(dateObjesi);
    }

    private String formatSaat(Date dateObjesi) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObjesi);
    }

    // Deprem Şiddetinin 0.0 şeklinde göstermek için formatlama metodu.

    private String formatDepremSiddeti(double siddet){

        DecimalFormat formatSiddet = new DecimalFormat("0.0");
        return formatSiddet.format(siddet);

    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId; // Double değerin int hali tutulacak
        int magnitudeFloor = (int) Math.floor(magnitude); // tam sayı kısmını ayırıyoruz.
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        // ContextCombat ilgili resource id içindeki (int değer ) renk kodunu renk olarak almamızı sağlıyoruz.
        // Renk kaynağı kimliğini gerçek bir tamsayı kimliğine çevirme işlemi yapa.r
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
