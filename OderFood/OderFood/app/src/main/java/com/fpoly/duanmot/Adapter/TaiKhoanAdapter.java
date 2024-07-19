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

import com.fpoly.duanmot.DAO.TaiKhoanDAO;
import com.fpoly.duanmot.Model.TaiKhoan;
import com.fpoly.duanmot.R;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TaiKhoan> list;

    public TaiKhoanAdapter(Context context, ArrayList<TaiKhoan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_taikhoan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanAdapter.ViewHolder holder, int position) {

        final TaiKhoan taiKhoan = list.get(position);

        holder.tv_matk.setText("Mã: " + taiKhoan.getMatk());
        holder.tv_hoten.setText("" + taiKhoan.getHoten());
        holder.tv_taikhoan.setText("Tài khoản: " + taiKhoan.getTaikhoan());
        holder.tv_matkhau.setText("Mật khẩu: " + taiKhoan.getMatkhau());
        holder.tv_sdt.setText("SĐT: " + taiKhoan.getSdt());
        holder.tv_diachi.setText("Địa chỉ: " + taiKhoan.getDiachi());
        holder.tv_loaitaikhoan.setText("" + taiKhoan.getLoaitaikhoan() + " ");

        holder.img_chucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.img_chucnang);
                popupMenu.inflate(R.menu.menu_chucnang);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu_edit){
                            showDialogEdit(taiKhoan);
                        }
                        if (item.getItemId() == R.id.menu_delete){
                            int maTK = taiKhoan.getMatk();
                            String taikhoan = taiKhoan.getTaikhoan();
                            showDiaLogDelete(maTK, taikhoan);
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
        ImageView img_chucnang;
        TextView tv_matk, tv_hoten, tv_taikhoan, tv_matkhau, tv_sdt, tv_diachi, tv_loaitaikhoan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_matk = itemView.findViewById(R.id.tv_matk);
            tv_taikhoan = itemView.findViewById(R.id.tv_taikhoan);
            tv_matkhau = itemView.findViewById(R.id.tv_matkhau);
            tv_hoten = itemView.findViewById(R.id.tv_hoten);
            tv_sdt = itemView.findViewById(R.id.tv_sdt);
            tv_diachi = itemView.findViewById(R.id.tv_diachi);
            tv_loaitaikhoan = itemView.findViewById(R.id.tv_loaitaikhoan);

            img_chucnang = itemView.findViewById(R.id.img_chucnang);

        }
    }

    private void showDialogEdit(TaiKhoan taiKhoan){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_taikhoan, null);
        BottomSheetDialog sheetDialog = new BottomSheetDialog(context);
        sheetDialog.setContentView(view);
        sheetDialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
        TextView tv_maTK = sheetDialog.findViewById(R.id.tv_maTK);
        EditText ed_taikhoan = sheetDialog.findViewById(R.id.ed_taikhoan);
        EditText ed_matkhau = sheetDialog.findViewById(R.id.ed_matkhau);
        EditText ed_hoten = sheetDialog.findViewById(R.id.ed_hoten);
        EditText ed_sdt = sheetDialog.findViewById(R.id.ed_sdt);
        EditText ed_diachi = sheetDialog.findViewById(R.id.ed_diachi);

        TextView btn_edit = sheetDialog.findViewById(R.id.btn_edit);
        TextView btn_cancel = sheetDialog.findViewById(R.id.btn_cancel);

        tv_maTK.setText("Mã TK: " + taiKhoan.getMatk());
        ed_taikhoan.setText(taiKhoan.getTaikhoan());
        ed_matkhau.setText(taiKhoan.getMatkhau());
        ed_hoten.setText(taiKhoan.getHoten());
        ed_sdt.setText(taiKhoan.getSdt());
        ed_diachi.setText(taiKhoan.getDiachi());

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(context);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maTK = taiKhoan.getMatk();
                String taikhoan = ed_taikhoan.getText().toString();
                String matkhau = ed_matkhau.getText().toString();
                String hoten = ed_hoten.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String diachi = ed_diachi.getText().toString();

                if (taikhoan.isEmpty()) {
                    ed_taikhoan.setError("Nhập tài khoản");
                    return;
                }
                if (matkhau.isEmpty()) {
                    ed_matkhau.setError("Nhập mật khẩu");
                    return;
                }
                if (hoten.isEmpty()) {
                    ed_hoten.setError("Nhập họ tên");
                    return;
                }
                if (sdt.isEmpty()) {
                    ed_sdt.setError("Nhập số điện thoại");
                    return;
                }
                if (diachi.isEmpty()) {
                    ed_diachi.setError("Nhập địa chỉ");
                    return;
                }
                TaiKhoan taiKhoan = new TaiKhoan(maTK, taikhoan, matkhau, hoten, sdt, diachi);
                boolean check = taiKhoanDAO.suaTaiKhoan(taiKhoan);
                if (check){
                    notifyDataSetChanged();
                    list.clear();
                    list = taiKhoanDAO.getDSTaiKhoan();
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

    private void showDiaLogDelete(int maTK, String taikhoan) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setIcon(R.drawable.logo_delete);
        dialogDelete.setTitle("Bạn có chắc chắn muốn xóa tài khoản " + taikhoan + " không ?");
        dialogDelete.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogDelete.setNegativeButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(context);
                int check = taiKhoanDAO.xoaTaiKhoan(maTK);
                if (check == 1){
                    notifyDataSetChanged();
                    list.clear();
                    list = taiKhoanDAO.getDSTaiKhoan();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else if (check == -1) {
                    Toast.makeText(context, "Không thể xóa, vì đã tồn tại trong hóa đơn", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogDelete.create();
        dialogDelete.show();
    }
}
