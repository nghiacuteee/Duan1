package com.fpoly.duanmot.Model;

public class TheLoai {
    private int maloai;
    private String tenloai;

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

    public TheLoai(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }
}
