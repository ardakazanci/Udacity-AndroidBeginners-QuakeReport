package com.example.android.quakereport;

public class Model {

    private String depremSiddeti;
    private String depremKonum;
    private String depremTarih;

    public Model(String depremSiddeti, String depremKonum, String depremTarih) {
        this.depremSiddeti = depremSiddeti;
        this.depremKonum = depremKonum;
        this.depremTarih = depremTarih;
    }

    public String getDepremSiddeti() {
        return depremSiddeti;
    }

    public void setDepremSiddeti(String depremSiddeti) {
        this.depremSiddeti = depremSiddeti;
    }

    public String getDepremKonum() {
        return depremKonum;
    }

    public void setDepremKonum(String depremKonum) {
        this.depremKonum = depremKonum;
    }

    public String getDepremTarih() {
        return depremTarih;
    }

    public void setDepremTarih(String depremTarih) {
        this.depremTarih = depremTarih;
    }
}
