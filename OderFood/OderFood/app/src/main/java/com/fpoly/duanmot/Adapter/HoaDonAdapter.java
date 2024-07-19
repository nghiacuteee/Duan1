package com.fpoly.duanmot.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.fpoly.duanmot.DAO.HoaDonDAO;
import com.fpoly.duanmot.DAO.SanPhamDAO;
import com.fpoly.duanmot.Model.HoaDon;
import com.fpoly.duanmot.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HoaDon> list;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder holder, int position) {

        final HoaDon hoaDon = list.get(holder.getAdapterPosition());

        HoaDonDAO hoaDonDAO = new HoaDonDAO(context);

        holder.tv_mahd.setText("Mã HĐ: " + hoaDon.getMahd());
        holder.tv_tongsanpham.setText("" + hoaDon.getTongsanpham());
        holder.tv_matk.setText("Mã tài khoản: " + hoaDon.getMatk());
        holder.tv_ngaylap.setText("" + hoaDon.getNgaylap());
        holder.tv_hoten.setText("Người nhận: " + hoaDon.getHoten());
        holder.tv_sdt.setText("SĐT: " + hoaDon.getSdt());
        holder.tv_diachi.setText("Giao đến: " + hoaDon.getDiachi());
        holder.tv_tongtien.setText("Số tiền: " + hoaDon.getTongtien() + " VNĐ");

        if (hoaDon.getTrangthai() == 1){
            holder.tv_trangthai.setText("Đã nhận hàng");
            holder.tv_trangthai.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.tv_trangthai.setText("Chưa nhận hàng");
            holder.tv_trangthai.setTextColor(ContextCompat.getColor(context, R.color.blue));
        }

        holder.img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maHD = hoaDon.getMahd();
                showDiaLogDeleteHD(maHD);
            }
        });

        holder.btn_doitrangthai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean KiemTra = hoaDonDAO.thayDoiTrangThai(hoaDon);
                if (KiemTra){
                    list.clear();
                    list = hoaDonDAO.getDSHoaDon();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Thay đổi trạng thái thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_mahd, tv_ngaylap, tv_hoten, tv_sdt, tv_diachi, tv_tongtien, tv_tongsanpham, tv_trangthai, tv_matk, btn_doitrangthai;
        ImageView img_xoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_mahd = itemView.findViewById(R.id.tv_mahd);
            tv_hoten = itemView.findViewById(R.id.tv_hoten);
            tv_sdt = itemView.findViewById(R.id.tv_sdt);
            tv_diachi = itemView.findViewById(R.id.tv_diachi);
            tv_tongtien = itemView.findViewById(R.id.tv_tongtien);
            tv_tongsanpham = itemView.findViewById(R.id.tv_tongsanpham);
            tv_matk = itemView.findViewById(R.id.tv_matk);
            tv_trangthai = itemView.findViewById(R.id.tv_trangthai);
            btn_doitrangthai = itemView.findViewById(R.id.btn_doiTT);
            tv_ngaylap = itemView.findViewById(R.id.tv_ngaylap);

            img_xoa = itemView.findViewById(R.id.img_xoa);
        }
    }

    private void showDiaLogDeleteHD(int maHD){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setIcon(R.drawable.logo_delete);
        dialogDelete.setTitle("Bạn có chắc chắn muốn xóa hóa đơn mã " + maHD + " không ?");
        dialogDelete.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogDelete.setNegativeButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                boolean check = hoaDonDAO.xoaHoaDon(maHD);
                if (check){
                    list.clear();
                    list = hoaDonDAO.getDSHoaDon();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogDelete.create();
        dialogDelete.show();

    }

}
