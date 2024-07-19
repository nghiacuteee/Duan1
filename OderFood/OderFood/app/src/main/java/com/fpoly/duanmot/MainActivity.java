package com.fpoly.duanmot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duanmot.DAO.TaiKhoanDAO;
import com.fpoly.duanmot.Fragment.FragmentThongTinApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import com.fpoly.duanmot.Fragment.FragmentCuaHang;
import com.fpoly.duanmot.Fragment.FragmentGioHang;
import com.fpoly.duanmot.Fragment.FragmentHoaDon;
import com.fpoly.duanmot.Fragment.FragmentQLHoaDon;
import com.fpoly.duanmot.Fragment.FragmentQLTaiKhoan;
import com.fpoly.duanmot.Fragment.FragmentQLTheLoai;
import com.fpoly.duanmot.Fragment.FragmentQLSanPham;
import com.fpoly.duanmot.Fragment.FragmentTaiKhoan;
import com.fpoly.duanmot.Fragment.FragmentThongKe;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationViewDrawer;
    private Fragment fragment;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        navigationViewDrawer = findViewById(R.id.navigationDrawer);

        drawerLayout = findViewById(R.id.drawerLayout);
        View headerLayout = navigationViewDrawer.getHeaderView(0);
        TextView tv_hoten = headerLayout.findViewById(R.id.tv_hoten);
        TextView tv_taikhoan = headerLayout.findViewById(R.id.tv_taikhoan);

        // Lấy thông tin hoten tentk từ khách hàng
        SharedPreferences sharedPreferences = this.getSharedPreferences("ThongTinTaiKhoan", MODE_PRIVATE);
        Integer maTK = sharedPreferences.getInt("matk", 0);
        String hoten = sharedPreferences.getString("hoten", "");
        String taikhoan = sharedPreferences.getString("taikhoan", "");
        String loaiTK = sharedPreferences.getString("loaitaikhoan", "");
        tv_hoten.setText(hoten);
        tv_taikhoan.setText(taikhoan);

        FragmentCuaHang fragmentCuaHang = new FragmentCuaHang();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentCuaHang).commit();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút menu
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mCuaHang){
                    fragment = new FragmentCuaHang();
                }

                if (item.getItemId() == R.id.mGioHang){
                    fragment = new FragmentGioHang();
                }

                if (item.getItemId() == R.id.mHoaDon){
                    fragment = new FragmentHoaDon();
                }

                if (item.getItemId() == R.id.mTaiKhoan){
                    fragment = new FragmentTaiKhoan();
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolbar.setTitle( item.getTitle() );
                }

                return true;
            }
        });

        navigationViewDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mQLTrangChu){
                    fragment = new FragmentCuaHang();
                }

                if (item.getItemId() == R.id.mQLSanPham){
                    fragment = new FragmentCuaHang();
                }

                if (item.getItemId() == R.id.mQLSanPham){
                    fragment = new FragmentQLSanPham();
                }

                if (item.getItemId() == R.id.mQLTheLoai){
                    fragment = new FragmentQLTheLoai();
                }

                if (item.getItemId() == R.id.mQLHoaDon){
                    fragment = new FragmentQLHoaDon();
                }

                if (item.getItemId() == R.id.mQLTaiKhoan){
                    fragment = new FragmentQLTaiKhoan();
                }

                if (item.getItemId() == R.id.mThongKe){
                    fragment = new FragmentThongKe();
                }

                if (item.getItemId() == R.id.mDoiMatKhau){
                    showDialogDoiMK(maTK);
                }

                if (item.getItemId() == R.id.mThongTinApp){
                    fragment = new FragmentThongTinApp();
                }

                if (item.getItemId() == R.id.mDangXuat){
                    Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        // Chức năng cho Admin
        if (!loaiTK.equals("admin")){
            Menu menu = navigationViewDrawer.getMenu();
            menu.findItem(R.id.mQLHoaDon).setVisible(false); // Ẩn chúc năng
            menu.findItem(R.id.mQLSanPham).setVisible(false);
            menu.findItem(R.id.mQLTaiKhoan).setVisible(false);
            menu.findItem(R.id.mQLTheLoai).setVisible(false);
            menu.findItem(R.id.mThongKe).setVisible(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){ // id của nút home
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showDialogDoiMK(int matk){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_doi_matkhau, null);
        BottomSheetDialog dialogAdd = new BottomSheetDialog(this);
        dialogAdd.setContentView(view);
        dialogAdd.setCanceledOnTouchOutside(false);

        EditText ed_matkhaucu = view.findViewById(R.id.ed_matkhaucu);
        EditText ed_matkhaumoi = view.findViewById(R.id.ed_matkhaumoi);
        EditText ed_confirm_matkhaumoi = view.findViewById(R.id.ed_confirm_matkhaumoi);

        TextView btn_add = view.findViewById(R.id.btn_add);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matkhaucu = ed_matkhaucu.getText().toString();
                String matkhaumoi = ed_matkhaumoi.getText().toString();
                String confirm_matkhaumoi = ed_confirm_matkhaumoi.getText().toString();

                if (matkhaucu.isEmpty()){
                    ed_matkhaucu.setError("Vui lòng nhập mật khẩu cũ");
                    return;
                }
                if (matkhaumoi.isEmpty()){
                    ed_matkhaumoi.setError("Vui lòng nhập mật khẩu mới");
                    return;
                }
                if (confirm_matkhaumoi.isEmpty()){
                    ed_confirm_matkhaumoi.setError("Vui lòng nhập lại mật khẩu mới");
                    return;
                }
                if (!confirm_matkhaumoi.equals(matkhaumoi)){
                    ed_confirm_matkhaumoi.setError("Mật khẩu không trùng nhau");
                    return;
                }

                TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(MainActivity.this);
                int check = taiKhoanDAO.doiMatKhau(matk, matkhaucu, matkhaumoi);
                if (check == -1){
                    Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                } else if (check == 0){
                    Toast.makeText(MainActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    dialogAdd.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dialogAdd.dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.dismiss();
            }
        });

        dialogAdd.show();
    }

}