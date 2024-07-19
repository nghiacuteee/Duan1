package com.fpoly.duanmot.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.fpoly.duanmot.Adapter.HoaDonKHAdapter;
import com.fpoly.duanmot.DAO.HoaDonDAO;
import com.fpoly.duanmot.Model.HoaDon;
import com.fpoly.duanmot.R;

public class FragmentHoaDon extends Fragment {

    RecyclerView recyclerHoaDon;
    ArrayList<HoaDon> list = new ArrayList<>();
    HoaDonDAO hoaDonDAO;
    int matk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hoadon, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("ThongTinTaiKhoan", MODE_PRIVATE);
        matk = sharedPreferences.getInt("matk", 0);

        recyclerHoaDon = view.findViewById(R.id.recyclerHD);
        hoaDonDAO = new HoaDonDAO(getContext());

        loadDataHoaDon();

        return view;
    }

    public void loadDataHoaDon() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerHoaDon.setLayoutManager(linearLayoutManager);

        list = hoaDonDAO.getDSHoaDonTheoKH(matk);
        HoaDonKHAdapter hoaDonKHAdapter = new HoaDonKHAdapter(getContext(), list);
        recyclerHoaDon.setAdapter(hoaDonKHAdapter);
    }

}
