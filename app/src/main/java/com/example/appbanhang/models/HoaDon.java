package com.example.appbanhang.models;

public class HoaDon {
    private String id;

    public HoaDon() {
    }

    public HoaDon(String id, int tongtien, String ngaytaodon, String ngayhoanthanh, String hoten, String sodienthoai, String diachi, String trangthai,String idUser) {
        this.id = id;
        this.tongtien = tongtien;
        this.ngaytaodon = ngaytaodon;
        this.ngayhoanthanh = ngayhoanthanh;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.trangthai = trangthai;
        this.idUser = idUser;
    }

    private int tongtien;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaytaodon() {
        return ngaytaodon;
    }

    public void setNgaytaodon(String ngaytaodon) {
        this.ngaytaodon = ngaytaodon;
    }

    public String getNgayhoanthanh() {
        return ngayhoanthanh;
    }

    public void setNgayhoanthanh(String ngayhoanthanh) {
        this.ngayhoanthanh = ngayhoanthanh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    private String ngaytaodon;
    private String ngayhoanthanh;
    private String hoten;
    private String sodienthoai;
    private String diachi;
    private String trangthai;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    private String idUser;
}
