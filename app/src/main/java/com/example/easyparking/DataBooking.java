package com.example.easyparking;

public class DataBooking {
    private String nama;
    private String plat;
    private String tempat;

    public DataBooking(String nama, String plat, String tempat) {
        this.nama = nama;
        this.plat = plat;
        this.tempat = tempat;
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

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
