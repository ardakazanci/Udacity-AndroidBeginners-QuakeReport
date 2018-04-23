package com.example.android.quakereport;

public class Model {

    private double depremSiddeti;
    private String depremKonum;
    private long depremTarih; // Deprem zamanı UNİX temelde olduğu için long değişken türünü kullandık.
    private String depremUrl;

    public Model(double depremSiddeti, String depremKonum, long depremTarih, String depremUrl) {
        this.depremSiddeti = depremSiddeti;
        this.depremKonum = depremKonum;
        this.depremTarih = depremTarih;
        this.depremUrl = depremUrl;
    }

    public double getDepremSiddeti() {
        return depremSiddeti;
    }

    public void setDepremSiddeti(double depremSiddeti) {
        this.depremSiddeti = depremSiddeti;
    }

    public String getDepremKonum() {
        return depremKonum;
    }

    public void setDepremKonum(String depremKonum) {
        this.depremKonum = depremKonum;
    }

    public long getDepremTarih() {
        return depremTarih;
    }

    public void setDepremTarih(long depremTarih) {
        this.depremTarih = depremTarih;
    }

    public String getDepremUrl() {
        return depremUrl;
    }

    public void setDepremUrl(String depremUrl) {
        this.depremUrl = depremUrl;
    }
}
