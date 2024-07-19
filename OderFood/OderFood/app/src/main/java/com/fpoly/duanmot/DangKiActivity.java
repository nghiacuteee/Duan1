package com.fpoly.duanmot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duanmot.DAO.TaiKhoanDAO;
import com.fpoly.duanmot.Model.TaiKhoan;
import com.fpoly.duanmot.R;

public class DangKiActivity extends AppCompatActivity {

    EditText ed_taikhoan, ed_matkhau, ed_matkhau_confim, ed_hoten, ed_sdt, ed_diachi;
    TextView btn_dangki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        ed_taikhoan = findViewById(R.id.ed_taikhoan);
        ed_matkhau = findViewById(R.id.ed_matkhau);
        ed_matkhau_confim = findViewById(R.id.ed_matkhau_confirm);
        ed_hoten = findViewById(R.id.ed_hoten);
        ed_sdt = findViewById(R.id.ed_sdt);
        ed_diachi = findViewById(R.id.ed_diachi);
        btn_dangki = findViewById(R.id.btn_dangki);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = ed_taikhoan.getText().toString();
                String matkhau = ed_matkhau.getText().toString();
                String matkhau_confirm = ed_matkhau_confim.getText().toString();
                String hoten = ed_hoten.getText().toString();
                String diachi = ed_diachi.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String loaitaikhoan = "khách hàng";

                if (taikhoan.isEmpty()){
                    ed_taikhoan.setError("Vui lòng nhập tài khoản");
                    return;
                }
                if (matkhau.isEmpty()){
                    ed_matkhau.setError("Vui lòng nhập mật khẩu");
                    return;
                }
                if (matkhau_confirm.isEmpty()){
                    ed_matkhau_confim.setError("Vui lòng nhập lại mật khẩu");
                    return;
                }
                if (hoten.isEmpty()){
                    ed_taikhoan.setError("Vui lòng nhập họ tên");
                    return;
                }
                if (sdt.isEmpty()){
                    ed_sdt.setError("Vui lòng nhập số điện thoại");
                    return;
                }
                if (diachi.isEmpty()){
                    ed_taikhoan.setError("Vui lòng nhập địa chỉ");
                    return;
                }
                if (!matkhau.equals(matkhau_confirm)){
                    ed_matkhau_confim.setError("Confirm pass không trùng password");
                    return;
                }

                TaiKhoan taiKhoan = new TaiKhoan(taikhoan, matkhau, hoten, sdt, diachi, loaitaikhoan);

                if (taiKhoanDAO.checkDangKi(taiKhoan)){
                    Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));
                } else {
                    Toast.makeText(DangKiActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}