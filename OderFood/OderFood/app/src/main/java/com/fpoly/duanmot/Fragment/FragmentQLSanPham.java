package com.fpoly.duanmot.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpoly.duanmot.Model.HoaDon;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.fpoly.duanmot.Adapter.SanPhamAdapter;
import com.fpoly.duanmot.ConvertData;
import com.fpoly.duanmot.DAO.SanPhamDAO;
import com.fpoly.duanmot.DAO.TheLoaiDAO;
import com.fpoly.duanmot.Model.SanPham;
import com.fpoly.duanmot.Model.TheLoai;
import com.fpoly.duanmot.R;

public class FragmentQLSanPham extends Fragment {

    private ImageView img_sanpham;
    private SanPhamDAO sanPhamDAO;
    private RecyclerView recyclerSanPham;
    private ArrayList<SanPham> list;
    private ArrayList<SanPham> listTemp;
    private final int REQUESTCODE_FOLDER = 777;
    private final int REQUESTCODE_CAMERA = 999;
    private Bitmap bitmapImages = null;
    private SanPhamAdapter sanPhamAdapter;

    public FragmentQLSanPham() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_quanly_sanpham, container, false);

        recyclerSanPham = view.findViewById(R.id.recyclerSanPham);
        EditText ed_search = view.findViewById(R.id.ed_timkiem);
        sanPhamDAO = new SanPhamDAO(getContext());

        list = sanPhamDAO.getDSSanPham();
        listTemp = sanPhamDAO.getDSSanPham();
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list.clear();
                for (SanPham sanPham : listTemp){
                    if ( String.valueOf(sanPham.getMasp()).contains(s.toString()) || sanPham.getTensp().contains(s.toString())){
                        list.add(sanPham);
                    }
                    sanPhamAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        loadDataSanPham();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSanPham.setLayoutManager(linearLayoutManager);
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        recyclerSanPham.setAdapter(sanPhamAdapter);

        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemSanPham();
            }
        });

        return view;
    }

    private void showDialogThemSanPham(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_sanpham, null);
        BottomSheetDialog dialogAdd = new BottomSheetDialog(getContext());
        dialogAdd.setContentView(view);
        dialogAdd.setCanceledOnTouchOutside(false);

        EditText ed_tensp = view.findViewById(R.id.ed_tensp);
        img_sanpham = view.findViewById(R.id.img_sanpham);
        Spinner spn_theloai = view.findViewById(R.id.spn_theloai);
        EditText ed_motasp = view.findViewById(R.id.ed_motasp);
        EditText ed_giasp = view.findViewById(R.id.ed_giasp);
        TextView btn_add = view.findViewById(R.id.btn_add);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel);

        getDataTheLoai(spn_theloai);

        ImageView img_folder = view.findViewById(R.id.img_folder);
        img_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUESTCODE_FOLDER);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy mã sách
                HashMap<String, Object> hmSach = (HashMap<String, Object>) spn_theloai.getSelectedItem();
                int maloai = (int) hmSach.get("maloai");

                String tensp = ed_tensp.getText().toString();
                String motasp = ed_motasp.getText().toString();
                int giasp = Integer.parseInt(ed_giasp.getText().toString());
                byte[] img_sp = ConvertData.ConvertImages(bitmapImages);

                SanPham sanPham = new SanPham(tensp, img_sp, motasp, giasp, maloai);
                boolean KiemTra = sanPhamDAO.themSanPham(sanPham);
                if (KiemTra){
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    loadDataSanPham();
                    dialogAdd.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESTCODE_CAMERA && resultCode == Activity.RESULT_OK && data != null) ;
        {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                bitmapImages = bitmap;
            } catch (Exception e) {
                Log.e("Error", "" + e);
            }
            Toast.makeText(getContext(), "Thêm ảnh thành công !", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUESTCODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmapImages = bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "Thêm ảnh thành công !", Toast.LENGTH_SHORT).show();
            img_sanpham.setImageBitmap(bitmapImages);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ArrayList<HashMap<String, Object>> getDataTheLoai(Spinner spn_theloai){
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(getContext());
        ArrayList<TheLoai> list = theLoaiDAO.getDSTheLoai();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        // Đổi ds TL sang thành HashMap
        for (TheLoai theLoai : list){
            HashMap<String, Object> loai = new HashMap<>();
            loai.put("maloai", theLoai.getMaloai());
            loai.put("tenloai", theLoai.getTenloai());
            listHM.add(loai);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"tenloai"}, new int[]{android.R.id.text1});
        spn_theloai.setAdapter(simpleAdapter);
        return  listHM;
    }

    public void loadDataSanPham(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSanPham.setLayoutManager(linearLayoutManager);

        list = sanPhamDAO.getDSSanPham();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        recyclerSanPham.setAdapter(sanPhamAdapter);
    }
}
