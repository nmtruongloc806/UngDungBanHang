package com.example.appbanhang.models;

public class User {


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

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public User(String id,String hoten, String sodienthoai, String matkhau, String diachi, String ngaysinh, String gioitinh,String tenLoai,String ngaythamgia,Boolean hoatdong) {
        this.id = id;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.matkhau = matkhau;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.tenLoai = tenLoai;
        this.ngaythamgia = ngaythamgia;
        this.hoatdong = hoatdong;
    }
    public User(){

    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getGioitinh() {
        return gioitinh;
    }


    public String getNgaythamgia() {
        return ngaythamgia;
    }

    public void setNgaythamgia(String ngaythamgia) {
        this.ngaythamgia = ngaythamgia;
    }

    public String ngaythamgia;
    public String tenLoai;
    public String hoten;
    public String sodienthoai;
    public String matkhau;
    public String diachi;
    public String ngaysinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String id;


    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String gioitinh;

    public Boolean getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(Boolean hoatdong) {
        this.hoatdong = hoatdong;
    }

    public Boolean hoatdong;
}
