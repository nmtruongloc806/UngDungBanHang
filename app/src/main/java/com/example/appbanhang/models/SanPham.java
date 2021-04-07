package com.example.appbanhang.models;

public class SanPham {
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String ID;
    public String tenSP;
    public String hinhSP;
    public int giaSP;
    public String giaSPStr;
    public boolean isYeuThich;

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int tongtien;
    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int soluong = 0;

    public boolean isYeuThich() {
        return isYeuThich;
    }
    public void setYeuThich(boolean yeuThich) {
        isYeuThich = yeuThich;
    }

    public String getGiaSPStr() {
        return giaSPStr;
    }

    public void setGiaSPStr(String giaSPStr) {
        this.giaSPStr = giaSPStr;
    }

    public String tenTH;
    public String motaSP;
    public int idTH;

    public int getIdTH() {
        return idTH;
    }

    public void setIdTH(int idTH) {
        this.idTH = idTH;
    }

    public String getMotaSP() {
        return motaSP;
    }

    public void setMotaSP(String motaSP) {
        this.motaSP = motaSP;
    }

    public SanPham(String ID, String tenSP, String hinhSP, int giaSP, String tenTH, String motaSP,int idTH,String giaSPStr,boolean isYeuThich, int soluong,int tongtien) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.hinhSP = hinhSP;
        this.giaSP = giaSP;
        this.tenTH = tenTH;
        this.motaSP = motaSP;
        this.idTH = idTH;
        this.giaSPStr = giaSPStr;
        this.isYeuThich = isYeuThich;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }
}
