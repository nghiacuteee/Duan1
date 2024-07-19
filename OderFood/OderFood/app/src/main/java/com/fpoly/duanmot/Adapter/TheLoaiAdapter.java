package com.fpoly.duanmot.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import com.fpoly.duanmot.DAO.TheLoaiDAO;
import com.fpoly.duanmot.Model.TheLoai;
import com.fpoly.duanmot.R;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TheLoai> list;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_theloai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TheLoai theLoai = list.get(position);

        holder.tv_maloai.setText("Mã: " + String.valueOf(theLoai.getMaloai()));
        holder.tv_tenloai.setText("Thể loại: "+ theLoai.getTenloai());

        holder.img_chucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.img_chucnang);
                popupMenu.inflate(R.menu.menu_chucnang);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu_edit){
                            showDialogEdit(theLoai);
                        }
                        if (item.getItemId() == R.id.menu_delete){
                            int maloai = theLoai.getMaloai();
                            String tenloai = theLoai.getTenloai();
                            showDialogDelete(maloai, tenloai);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_maloai, tv_tenloai;
        ImageView img_chucnang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_maloai = itemView.findViewById(R.id.tv_maloai);
            tv_tenloai = itemView.findViewById(R.id.tv_tenloai);
            img_chucnang = itemView.findViewById(R.id.img_chucnang);

        }
    }

    private void showDialogEdit(TheLoai theLoai){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_theloai, null);
        BottomSheetDialog sheetDialog = new BottomSheetDialog(context);
        sheetDialog.setContentView(view);
        sheetDialog.setCanceledOnTouchOutside(false);

        TextView tv_maloai = sheetDialog.findViewById(R.id.tv_maloai);
        EditText ed_tenloai = sheetDialog.findViewById(R.id.ed_tenloai);
        TextView btn_edit = sheetDialog.findViewById(R.id.btn_edit);
        TextView btn_cancel = sheetDialog.findViewById(R.id.btn_cancel);

        tv_maloai.setText("Mã: " + theLoai.getMaloai());
        ed_tenloai.setText(theLoai.getTenloai());

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maloai = theLoai.getMaloai();
                String tenloai = ed_tenloai.getText().toString();

                if (tenloai.isEmpty()) {
                    ed_tenloai.setError("Nhập tên thể loại");
                    return;
                }

                boolean check = theLoaiDAO.suaTheLoai(maloai, tenloai);
                if (check){
                    notifyDataSetChanged();
                    list.clear();
                    list = theLoaiDAO.getDSTheLoai();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog.dismiss();
            }
        });
        sheetDialog.show();
    }

    private void showDialogDelete(int maSP, String tenloai){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setIcon(R.drawable.logo_delete);
        dialogDelete.setTitle("Bạn có chắc chắn muốn xóa thể loại " + tenloai + " không ?");
        dialogDelete.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogDelete.setNegativeButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
                int check = theLoaiDAO.xoaTheLoai(maSP);
                if (check == 1){
                    list.clear();
                    list = theLoaiDAO.getDSTheLoai();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else if (check == -1) {
                    Toast.makeText(context, "Không thể xóa, vì tồn tại trong sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogDelete.create();
        dialogDelete.show();

    }

}
