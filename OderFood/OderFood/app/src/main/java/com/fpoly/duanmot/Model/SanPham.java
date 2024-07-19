package com.fpoly.duanmot.Model;

public class SanPham {
    private int masp;
    private String tensp;
    private byte[] anhsp;
    private String motasp;
    private int giasp;
    private int maloai;
    private String tenloai;

    public SanPham(int masp, String tensp, byte[] anhsp, String motasp, int giasp) {
        this.masp = masp;
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.motasp = motasp;
        this.giasp = giasp;
    }

    public SanPham(String tensp, byte[] anhsp, String motasp, int giasp) {
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.motasp = motasp;
        this.giasp = giasp;
    }

    public SanPham(int masp, String tensp, byte[] anhsp, String tenloai, String motasp, int giasp) {
        this.masp = masp;
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.tenloai = tenloai;
        this.motasp = motasp;
        this.giasp = giasp;
    }

    public SanPham(String tensp, byte[] anhsp, String motasp, int giasp, int maloai) {
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.motasp = motasp;
        this.giasp = giasp;
        this.maloai = maloai;
    }

    public SanPham(int masp, String tensp, byte[] anhsp, String motasp, int giasp, int maloai) {
        this.masp = masp;
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.motasp = motasp;
        this.giasp = giasp;
        this.maloai = maloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public byte[] getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(byte[] anhsp) {
        this.anhsp = anhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }
}
