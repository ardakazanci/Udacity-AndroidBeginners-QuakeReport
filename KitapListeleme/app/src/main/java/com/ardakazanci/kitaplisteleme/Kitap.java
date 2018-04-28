package com.ardakazanci.kitaplisteleme;

public class Kitap {

    private String kitapBaslik;
    private String kitapAciklama;
    private String kitapAdres;

    public Kitap(String kitapBaslik, String kitapAciklama, String kitapAdres) {
        this.kitapBaslik = kitapBaslik;
        this.kitapAciklama = kitapAciklama;
        this.kitapAdres = kitapAdres;
    }

    public String getKitapBaslik() {
        return kitapBaslik;
    }

    public void setKitapBaslik(String kitapBaslik) {
        this.kitapBaslik = kitapBaslik;
    }

    public String getKitapAciklama() {
        return kitapAciklama;
    }

    public void setKitapAciklama(String kitapAciklama) {
        this.kitapAciklama = kitapAciklama;
    }

    public String getKitapAdres() {
        return kitapAdres;
    }

    public void setKitapAdres(String kitapAdres) {
        this.kitapAdres = kitapAdres;
    }
}
