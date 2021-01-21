package com.example.appbanhang.models;

public class ThuongHieu {
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTH() {
        return TenTH;
    }

    public void setTenTH(String tenTH) {
        TenTH = tenTH;
    }

    public String getHinhTH() {
        return HinhTH;
    }

    public void setHinhTH(String hinhTH) {
        HinhTH = hinhTH;
    }

    public int ID;
    public String TenTH;
    public String HinhTH;

    public ThuongHieu(int ID, String tenTH, String hinhTH) {
        this.ID = ID;
        TenTH = tenTH;
        HinhTH = hinhTH;
    }
}
