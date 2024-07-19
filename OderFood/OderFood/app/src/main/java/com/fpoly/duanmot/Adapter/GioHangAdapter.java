package com.fpoly.duanmot.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.fpoly.duanmot.DAO.GioHangDAO;
import com.fpoly.duanmot.Model.GioHang;
import com.fpoly.duanmot.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GioHang> list;
    private GioHangDAO gioHangDAO;

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {

        final GioHang gioHang = list.get(holder.getAdapterPosition());
        gioHangDAO = new GioHangDAO(context);

        byte[] image = gioHang.getAnhsp();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        holder.img_anhsp.setImageBitmap(bitmap);

        holder.tv_tensp.setText("" + gioHang.getTensp());
        holder.tv_soluong.setText("x" + gioHang.getSoluong());
        holder.tv_giasp.setText("Giá: " + gioHang.getGiasp() + " VNĐ");

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maGH = gioHang.getId();
                showDiaLogDelete(maGH);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_anhsp, img_delete;
        TextView tv_tensp, tv_giasp, tv_soluong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_anhsp = itemView.findViewById(R.id.img_anhsp);
            tv_tensp = itemView.findViewById(R.id.tv_tensp);
            tv_giasp = itemView.findViewById(R.id.tv_giasp);
            tv_soluong = itemView.findViewById(R.id.tv_soluong);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }

    private void showDiaLogDelete(int maGH){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setIcon(R.drawable.logo_delete);
        dialogDelete.setTitle("Bạn có muốn sản phẩm này khỏi giỏ hàng không ?");
        dialogDelete.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogDelete.setNegativeButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GioHangDAO gioHangDAO = new GioHangDAO(context);
                boolean check = gioHangDAO.xoaSPGioHang(maGH);
                notifyDataSetChanged();
                if (check){
                    list.clear();
                    list = gioHangDAO.getDSGioHang();
                    Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogDelete.create();
        dialogDelete.show();
    }
}
