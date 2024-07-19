package com.fpoly.duanmot.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.fpoly.duanmot.Database.DBHelper;
import com.fpoly.duanmot.Model.SanPham;

public class SanPhamDAO {

    private DBHelper dbHelper;

    public SanPhamDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    // Lấy danh sách sản phẩm
    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sp.masp, sp.tensp, sp.anhsp, loai.tenloai, sp.motasp, sp.giasp  FROM SANPHAM sp, THELOAI loai WHERE sp.maloai = loai.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //
    // Tìm sản phẩm theo loại
    public ArrayList<SanPham> getDSSanPhamTheoLoai(Integer maloai){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sp.masp, sp.tensp, sp.anhsp, loai.tenloai, sp.motasp, sp.giasp  FROM SANPHAM sp, THELOAI loai WHERE sp.maloai = loai.maloai AND sp.maloai = ?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPham sanPham){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("anhsp", sanPham.getAnhsp());
        contentValues.put("maloai", sanPham.getMaloai());
        contentValues.put("motasp", sanPham.getMotasp());
        contentValues.put("giasp", sanPham.getGiasp());

        long check = db.insert("SANPHAM", null, contentValues);
        if (check == -1){
            return false;
        } else {
            return true;
        }
    }

    // Sủa thông tin sản phẩm
    public boolean suaSanPham(SanPham sanPham){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.getTensp());
        contentValues.put("anhsp", sanPham.getAnhsp());
        contentValues.put("motasp",sanPham.getMotasp());
        contentValues.put("giasp", sanPham.getGiasp());
        contentValues.put("maloai", sanPham.getMaloai());
        long check = db.update("SANPHAM", contentValues, "masp = ?", new String[]{String.valueOf(sanPham.getMasp())});
        if (check == -1) {
            return false;
        }
        return true;
    }

    // Xóa sản phẩm
    public int xoaSanPham(int maSP){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        Cursor cursor = db.rawQuery("SELECT * FROM PHIEUMUON WHERE masach = ?", new String[]{String.valueOf(masach)});
//        if (cursor.getCount() != 0){
//            return -1;
//        }
        long check = db.delete("SANPHAM", "masp = ?", new String[]{String.valueOf(maSP)});
        if (check == -1){
            return 0;
        }
        return 1;
    }
}

