package com.fpoly.duanmot.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.duanmot.Model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.fpoly.duanmot.Adapter.HoaDonAdapter;
import com.fpoly.duanmot.DAO.HoaDonDAO;
import com.fpoly.duanmot.Model.HoaDon;
import com.fpoly.duanmot.R;

public class FragmentQLHoaDon extends Fragment {

    private RecyclerView recyclerHoaDon;
    private HoaDonDAO hoaDonDAO;
    private HoaDonAdapter hoaDonAdapter;
    private ArrayList<HoaDon> list;
    private ArrayList<HoaDon> listTemp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_quanly_hoadon, container, false);

        recyclerHoaDon = view.findViewById(R.id.recyclerHoaDon);
        EditText ed_search = view.findViewById(R.id.ed_timkiem);

        hoaDonDAO = new HoaDonDAO(getContext());

//        loadDataHoaDon();

        list = hoaDonDAO.getDSHoaDon();
        listTemp = hoaDonDAO.getDSHoaDon();
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list.clear();
                for (HoaDon hoaDon : listTemp){
                    if ( String.valueOf(hoaDon.getMahd()).contains(s.toString()) || hoaDon.getHoten().contains(s.toString())){
                        list.add(hoaDon);
                    }
                    hoaDonAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerHoaDon.setLayoutManager(linearLayoutManager);
        hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        recyclerHoaDon.setAdapter(hoaDonAdapter);

        return view;
    }

    private void loadDataHoaDon(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerHoaDon.setLayoutManager(linearLayoutManager);

        list = hoaDonDAO.getDSHoaDon();

        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        recyclerHoaDon.setAdapter(hoaDonAdapter);
    }

//    private void showDialogThemTheLoai(){
//        AlertDialog.Builder dialogThem = new AlertDialog.Builder(getContext());
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_theloai, null );
//        dialogThem.setView(view);
//
//        EditText ed_tenloai = view.findViewById(R.id.ed_tenloai);
//
//        dialogThem.setNegativeButton("THÊM", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String tenloai = ed_tenloai.getText().toString();
//
//                boolean KiemTra = theLoaiDAO.themTheLoai(tenloai);
//                if (KiemTra){
//                    Toast.makeText(getContext(), "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
//                    loadDataLoaiSach();
//                } else {
//                    Toast.makeText(getContext(), "Thêm thể loại thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        dialogThem.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        dialogThem.create();
//        dialogThem.show();
//    }

}
