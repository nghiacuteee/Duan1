package com.fpoly.duanmot.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "OderFood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbSANPHAM = "create table SANPHAM(masp integer primary key autoincrement, tensp text, maloai integer, anhsp blob, motasp text, giasp integer)";

        String dbTHELOAI = "CREATE TABLE THELOAI(maloai integer primary key autoincrement, tenloai text)";

        String dbGIOHANG = "create table GIOHANG(id integer primary key autoincrement, tensp text, anhsp blob, giasp integer, soluong integer)";

        String dbHOADON = "create table HOADON(mahd integer primary key autoincrement, ngaylap text, matk integer references TAIKHOAN(matk), hoten text, sdt text, diachi text, tongtien integer, tongsanpham text, trangthai integer)";

        String dbTAIKHOAN = "create table TAIKHOAN(matk integer primary key autoincrement, taikhoan text, matkhau text, hoten text, sdt text, diachi text, loaitaikhoan text)";

        db.execSQL(dbTHELOAI);
        db.execSQL(dbSANPHAM);
        db.execSQL(dbGIOHANG);
        db.execSQL(dbHOADON);
        db.execSQL(dbTAIKHOAN);

        db.execSQL("insert into THELOAI values (1, 'Cơm'), (2, 'Cháo'), (3, 'Bún, Phở'), (4, 'Đồ uống')");

        db.execSQL("insert into HOADON values (1, '09/11/2023', 1, 'Lăng Văn Thoại', '0862556262', 'Kim Chung - Hoài Đức - Hà Nội', 90000, 'Cháo gà x1', 0), (2, '03/11/2023', 2, 'Trần Tuấn Đạt', '0979991999', 'Duy Tiên - Hà Nam', 110000, 'Cơm bò x1', 1)");

        db.execSQL("insert into TAIKHOAN values (1, 'admin', '123', 'Lăng Văn Thoại', '0862556262', 'Kim Chung - Hoài Đức - Hà Nội','admin'), (2, 'ad01', '123', 'Trần Tuấn Đạt', '0979991999', 'Duy Tiên - Hà Nam','khách hàng')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
