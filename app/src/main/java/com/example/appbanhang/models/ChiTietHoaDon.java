package com.example.appbanhang.models;

public class ChiTietHoaDon {
    public ChiTietHoaDon(String id, String idSP, String idHD, int soluong) {
        this.id = id;
        this.idSP = idSP;
        this.idHD = idHD;
        this.soluong = soluong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    private String id;
    private String idSP;
    private String idHD;
    private int soluong;
}
