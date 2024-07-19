package com.fpoly.duanmot;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duanmot.DAO.GioHangDAO;
import com.fpoly.duanmot.Model.GioHang;

public class ChiTietSanPham extends AppCompatActivity {

    private TextView tv_tenSP, tv_giaSP, tv_motaSP, tv_soluong;
    private ImageView img_anhSP, img_cong, img_tru, img_back;
    private TextView btn_themGioHang, btn_cuaHang;
    private GioHangDAO gioHangDAO;
    private int soluong = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_sanpham);

        gioHangDAO = new GioHangDAO(this);

        tv_tenSP = findViewById(R.id.tv_tenSP);
        tv_giaSP = findViewById(R.id.tv_giaSP);
        tv_motaSP = findViewById(R.id.tv_motaSP);
        img_anhSP = findViewById(R.id.img_anhSP);
        btn_themGioHang = findViewById(R.id.btn_themGioHang);
        tv_soluong = findViewById(R.id.tv_soluong);
        img_back = findViewById(R.id.img_back);
        img_cong = findViewById(R.id.img_cong);
        img_tru = findViewById(R.id.img_tru);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Số lượng sản phẩm
        tv_soluong.setText("" + soluong);
        img_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong > 1) {
                    soluong --;
                    tv_soluong.setText("" + soluong);
                }
            }
        });

        img_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong ++;
                tv_soluong.setText("" + soluong);
            }
        });

        if (getIntent().getBundleExtra("dataSP") != null){
            Bundle bundle = getIntent().getBundleExtra("dataSP");

            byte[] byte_anhSP = bundle.getByteArray("anhsp");
            String tenSP = bundle.getString("tensp");
            String motaSP = bundle.getString("motasp");
            Integer giaSP = bundle.getInt("giasp");

            Bitmap bitmapAnhSP = ConvertData.ConvertBitmap(byte_anhSP);
            img_anhSP.setImageBitmap(bitmapAnhSP);

            tv_tenSP.setText(tenSP);
            tv_giaSP.setText(String.valueOf(giaSP));
            tv_motaSP.setText(motaSP);

            btn_themGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GioHang gioHang = new GioHang(tenSP, byte_anhSP, giaSP, soluong);
                    boolean themGioHang = gioHangDAO.themGioHang(gioHang);
                    if (themGioHang){
                        Toast.makeText(ChiTietSanPham.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChiTietSanPham.this, "Thêm giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}