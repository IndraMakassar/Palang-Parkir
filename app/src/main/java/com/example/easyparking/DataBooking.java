package com.example.easyparking;

public class DataBooking {
    private String nama;
    private String plat;
    private String type;
    private String warna;

    public DataBooking(String nama, String plat, String type, String warna) {
        this.nama = nama;
        this.plat = plat;
        this.type = type;
        this.warna = warna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
}
