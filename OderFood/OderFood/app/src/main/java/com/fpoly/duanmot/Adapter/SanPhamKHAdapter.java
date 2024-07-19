package com.fpoly.duanmot.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.fpoly.duanmot.ChiTietSanPham;
import com.fpoly.duanmot.Model.SanPham;
import com.fpoly.duanmot.R;

public class SanPhamKHAdapter extends RecyclerView.Adapter<SanPhamKHAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SanPham> list;

    public SanPhamKHAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham_kh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SanPham sanPham = list.get(position);

        byte[] image = sanPham.getAnhsp();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        holder.img_anhsp.setImageBitmap(bitmap);

        holder.tv_tensp.setText("" + sanPham.getTensp());

        String motasp = sanPham.getMotasp();
        if (motasp.length() > 40){
            String mota = motasp.substring(0, 40);
            holder.tv_motasp.setText("Mô tả: " + mota + "...");
        } else {
            holder.tv_motasp.setText("Mô tả: " + motasp);
        }

        holder.tv_giasp.setText("" + sanPham.getGiasp() + " VNĐ");

        holder.linear_sanpham_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tensp", sanPham.getTensp());
                bundle.putInt("giasp", sanPham.getGiasp());
                bundle.putString("motasp", sanPham.getMotasp());
                bundle.putByteArray("anhsp", sanPham.getAnhsp());

                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("dataSP", bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_anhsp;
        TextView tv_tensp, tv_motasp, tv_giasp, tv_loaisp;
        LinearLayout linear_sanpham_kh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_anhsp = itemView.findViewById(R.id.img_anhsp);
            tv_tensp = itemView.findViewById(R.id.tv_tensp);
            tv_motasp = itemView.findViewById(R.id.tv_motasp);
            tv_giasp = itemView.findViewById(R.id.tv_giasp);
            linear_sanpham_kh = itemView.findViewById(R.id.linear_sanpham_kh);


        }
    }
}
