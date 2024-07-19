package com.fpoly.duanmot.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.duanmot.ConvertData;
import com.fpoly.duanmot.Model.SanPham;
import com.fpoly.duanmot.Model.TheLoai;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import com.fpoly.duanmot.Adapter.TaiKhoanAdapter;
import com.fpoly.duanmot.DAO.TaiKhoanDAO;
import com.fpoly.duanmot.Model.TaiKhoan;
import com.fpoly.duanmot.R;

public class FragmentQLTaiKhoan extends Fragment {

    private RecyclerView recyclerTaiKhoan;
    private TaiKhoanDAO taiKhoanDAO;
    private TaiKhoanAdapter taiKhoanAdapter;
    private ArrayList<TaiKhoan> list;
    private ArrayList<TaiKhoan> listTemp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_quanly_taikhoan, container, false);

        recyclerTaiKhoan = view.findViewById(R.id.recyclerTaiKhoan);
        EditText ed_search = view.findViewById(R.id.ed_timkiem);

        taiKhoanDAO = new TaiKhoanDAO(getContext());

//        loadDataTaiKhoan();

        list = taiKhoanDAO.getDSTaiKhoan();
        listTemp = taiKhoanDAO.getDSTaiKhoan();
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list.clear();
                for (TaiKhoan taiKhoan : listTemp){
                    if ( taiKhoan.getTaikhoan().contains(s.toString()) || taiKhoan.getHoten().contains(s.toString())){
                        list.add(taiKhoan);
                    }
                    taiKhoanAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTaiKhoan.setLayoutManager(linearLayoutManager);
        taiKhoanAdapter = new TaiKhoanAdapter(getContext(), list);
        recyclerTaiKhoan.setAdapter(taiKhoanAdapter);

        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemTK();
            }
        });

        return view;
    }

    private void loadDataTaiKhoan(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTaiKhoan.setLayoutManager(linearLayoutManager);

        list = taiKhoanDAO.getDSTaiKhoan();

        TaiKhoanAdapter taiKhoanAdapter = new TaiKhoanAdapter(getContext(), list);
        recyclerTaiKhoan.setAdapter(taiKhoanAdapter);
    }

    private void showDialogThemTK(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_taikhoan, null);
        BottomSheetDialog dialogAdd = new BottomSheetDialog(getContext());
        dialogAdd.setContentView(view);
        dialogAdd.setCanceledOnTouchOutside(false);

        EditText ed_taikhoan = view.findViewById(R.id.ed_taikhoan);
        EditText ed_matkhau = view.findViewById(R.id.ed_matkhau);
        EditText ed_hoten = view.findViewById(R.id.ed_hoten);
        EditText ed_sdt = view.findViewById(R.id.ed_sdt);
        EditText ed_diachi = view.findViewById(R.id.ed_diachi);

        TextView btn_add = view.findViewById(R.id.btn_add);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = ed_taikhoan.getText().toString();
                String matkhau = ed_matkhau.getText().toString();
                String hoten = ed_hoten.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String diachi = ed_diachi.getText().toString();
                String loaitaikhoan = "khách hàng";

                if (taikhoan.isEmpty()){
                    ed_taikhoan.setError("Vui lòng nhập tài khoản");
                    return;
                }
                if (matkhau.isEmpty()){
                    ed_matkhau.setError("Vui lòng nhập mật khẩu");
                    return;
                }
                if (hoten.isEmpty()){
                    ed_hoten.setError("Vui lòng nhập họ tên");
                    return;
                }
                if (sdt.isEmpty()){
                    ed_sdt.setError("Vui lòng nhập số điện thoại");
                    return;
                }
                if (diachi.isEmpty()){
                    ed_diachi.setError("Vui lòng nhập địa chỉ");
                    return;
                }

                TaiKhoan taiKhoan = new TaiKhoan(taikhoan, matkhau, hoten, sdt, diachi, loaitaikhoan);
                int check = taiKhoanDAO.themTaiKhoan(taiKhoan);
                if (check == -1){
                    Toast.makeText(getContext(), "Tài khoản đã tồn tại, vui lòng tạo tài khoản khác", Toast.LENGTH_SHORT).show();
                } else if (check == 0){
                    Toast.makeText(getContext(), "Thêm tài khoản thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = taiKhoanDAO.getDSTaiKhoan();
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
