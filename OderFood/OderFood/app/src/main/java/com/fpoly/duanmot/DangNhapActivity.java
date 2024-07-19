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

public class DangNhapActivity extends AppCompatActivity {

    private EditText ed_taikhoan, ed_matkhau;
    private TextView btn_dangnhap;
    private TextView tv_dangki, tv_quenmatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);

        ed_taikhoan = findViewById(R.id.ed_taikhoan);
        ed_matkhau = findViewById(R.id.ed_matkhau);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        tv_dangki = findViewById(R.id.tv_dangki);
        tv_quenmatkhau = findViewById(R.id.tv_quenmatkhau);

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = ed_taikhoan.getText().toString();
                String matkhau = ed_matkhau.getText().toString();

                if (taikhoan.isEmpty()){
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (matkhau.isEmpty()){
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (taiKhoanDAO.checkDangNhap(taikhoan, matkhau)){
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    Toast.makeText(DangNhapActivity.this, "Hello, wellcome to App Order Food", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản hặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKiActivity.class));
            }
        });

        tv_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, QuenMatKhau.class));
            }
        });
    }
}