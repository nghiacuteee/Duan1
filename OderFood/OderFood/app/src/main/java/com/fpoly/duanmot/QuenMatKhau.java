package com.fpoly.duanmot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duanmot.DAO.TaiKhoanDAO;

public class QuenMatKhau extends AppCompatActivity {

    EditText ed_taikhoan, ed_matkhaumoi, ed_matkhaumoi_confirm;
    TextView btn_quenmatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        ed_taikhoan = findViewById(R.id.ed_taikhoan);
        ed_matkhaumoi = findViewById(R.id.ed_matkhaumoi);
        ed_matkhaumoi_confirm = findViewById(R.id.ed_matkhaumoi_confirm);
        btn_quenmatkhau = findViewById(R.id.btn_quenmatkhau);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        btn_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = ed_taikhoan.getText().toString();
                String matkhaumoi = ed_matkhaumoi.getText().toString();
                String matkhaumoi_confirm = ed_matkhaumoi_confirm.getText().toString();

                if (taikhoan.isEmpty()){
                    ed_taikhoan.setError("Vui lòng nhập tài khoản");
                    return;
                }
                if (matkhaumoi.isEmpty()){
                    ed_matkhaumoi.setError("Vui lòng nhập mật khẩu mới");
                    return;
                }
                if (matkhaumoi_confirm.isEmpty()){
                    ed_matkhaumoi_confirm.setError("Vui lòng nhập lại mật khẩu");
                    return;
                }
                if (!matkhaumoi.equals(matkhaumoi_confirm)){
                    ed_matkhaumoi_confirm.setError("Mật khẩu không trùng nhau");
                    return;
                }

                int check = taiKhoanDAO.quenMatKhau(taikhoan, matkhaumoi);
                if (check == -1){
                    Toast.makeText(QuenMatKhau.this, "Không tồn tại tài khoản như trên", Toast.LENGTH_SHORT).show();
                } else if (check == 0) {
                    Toast.makeText(QuenMatKhau.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuenMatKhau.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QuenMatKhau.this, DangNhapActivity.class));
                }
            }
        });

    }
}