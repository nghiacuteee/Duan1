package com.fpoly.duanmot.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.fpoly.duanmot.Database.DBHelper;
import com.fpoly.duanmot.Model.GioHang;

public class GioHangDAO {

    private DBHelper dbHelper;

    public GioHangDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    // Lấy danh sách sản phẩm
    public ArrayList<GioHang> getDSGioHang(){
        ArrayList<GioHang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GIOHANG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new GioHang(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    // Thêm sản phẩm
    public boolean themGioHang(GioHang gioHang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", gioHang.getTensp());
        contentValues.put("anhsp", gioHang.getAnhsp());
        contentValues.put("giasp", gioHang.getGiasp());
        contentValues.put("soluong", gioHang.getSoluong());

        long check = db.insert("GIOHANG", null, contentValues);
        if (check == -1){
            return false;
        } else {
            return true;
        }
    }

    // Xóa sạch Giỏ hàng
    public boolean clearGioHang(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long check = db.delete("GIOHANG", null, null);
        if (check == -1){
            return false;
        }
        return true;
    }

    // Xóa sản phẩm giỏ hàng
    public boolean xoaSPGioHang(int magh){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long check = db.delete("GIOHANG", "id = ?", new String[]{String.valueOf(magh)});
        if (check == -1){
            return false;
        }
        return true;
    }

}
