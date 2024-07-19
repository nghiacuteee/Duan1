package com.fpoly.duanmot.Model;

public class HoaDon {
    private int mahd;
    private String ngaylap;
    private int matk;
    private String hoten;
    private String sdt;
    private String diachi;
    private int tongtien;
    private String tongsanpham;
    private int trangthai;

    public HoaDon(int mahd, String ngaylap, int matk, int tongtien, int trangthai) {
        this.mahd = mahd;
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon(String ngaylap, int matk, int tongtien, int trangthai) {
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon(int mahd, String ngaylap, int matk, String hoten, String sdt, String diachi, int tongtien, int trangthai) {
        this.mahd = mahd;
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon(String ngaylap, int matk, String hoten, String sdt, String diachi, int tongtien, int trangthai) {
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon(String ngaylap, int matk, String hoten, String sdt, String diachi, int tongtien, String tongsanpham, int trangthai) {
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.tongsanpham = tongsanpham;
        this.trangthai = trangthai;
    }

    public HoaDon(int mahd, String ngaylap, int matk, String hoten, String sdt, String diachi, int tongtien, String tongsanpham, int trangthai) {
        this.mahd = mahd;
        this.ngaylap = ngaylap;
        this.matk = matk;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tongtien = tongtien;
        this.tongsanpham = tongsanpham;
        this.trangthai = trangthai;
    }

    public String getTongsanpham() {
        return tongsanpham;
    }

    public void setTongsanpham(String tongsanpham) {
        this.tongsanpham = tongsanpham;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
