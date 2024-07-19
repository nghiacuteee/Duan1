package com.fpoly.duanmot.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.fpoly.duanmot.Database.DBHelper;
import com.fpoly.duanmot.Model.TheLoai;

public class TheLoaiDAO {

    private DBHelper dbHelper;

    public TheLoaiDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    // Lấy danh sách thể loại
    public ArrayList<TheLoai> getDSTheLoai(){
        ArrayList<TheLoai> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM THELOAI", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new TheLoai(cursor.getInt(0), cursor.getString(1)) );
            } while (cursor.moveToNext());
        }
        return list;
    }

    // Thêm thể loại
    public boolean themTheLoai(String tenloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = db.insert("THELOAI", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }

    // Sửa thể loại
    public boolean suaTheLoai(int maloai, String tenloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);

        long check = db.update("THELOAI", contentValues, "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1) {
            return false;
        }
        return true;
    }

    // Xóa thể loại: 1: xóa thành công, -1: xóa thất bại, 0: có thể loại tồn tại trong mục sản phẩm
    public int xoaTheLoai(int maloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SANPHAM WHERE maloai = ?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("THELOAI", "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1){
            return 0;
        }
        return 1;
    }

}
