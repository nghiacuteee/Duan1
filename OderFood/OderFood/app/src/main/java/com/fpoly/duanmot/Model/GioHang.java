package com.fpoly.duanmot.Model;

public class GioHang {
    private int id;
    private String tensp;
    private byte[] anhsp;
    private int giasp;
    private int soluong;

    public GioHang(int id, String tensp, byte[] anhsp, int giasp, int soluong) {
        this.id = id;
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.giasp = giasp;
        this.soluong = soluong;
    }

    public GioHang(String tensp, byte[] anhsp, int giasp, int soluong) {
        this.tensp = tensp;
        this.anhsp = anhsp;
        this.giasp = giasp;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
