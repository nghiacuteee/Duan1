package com.fpoly.duanmot.Model;

public class TaiKhoan {
    private int matk;
    private String taikhoan;
    private String matkhau;
    private String hoten;
    private String sdt;
    private String diachi;
    private String loaitaikhoan;

    public TaiKhoan(int matk, String taikhoan, String matkhau, String hoten, String sdt, String diachi) {
        this.matk = matk;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public TaiKhoan(int matk, String taikhoan, String matkhau, String hoten, String sdt, String diachi, String loaitaikhoan) {
        this.matk = matk;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.loaitaikhoan = loaitaikhoan;
    }

    public TaiKhoan(String taikhoan, String matkhau, String hoten, String sdt, String diachi, String loaitaikhoan) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.loaitaikhoan = loaitaikhoan;
    }

    public TaiKhoan() {
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
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
