package com.example.appfood.MyView.Admin;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.InterSetOnItemClick;
import com.example.appfood.R;
import com.example.appfood.Activity_DangSanPham;

import java.text.NumberFormat;
import java.util.ArrayList;


public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private Activity context;
    private ArrayList<SanPham> arrayList;

    public AdapterProduct(Activity context, ArrayList<SanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SanPham sanPham = arrayList.get(position);
        holder.txttensp.setText(sanPham.getTensp());

        holder.txtgiasp.setText(NumberFormat.getInstance().format(sanPham.getGiatien()) + " Đ");
        Picasso.get().load(sanPham.getHinhanh()).into(holder.hinhanh);
        holder.SetOnItem(new InterSetOnItemClick() {
            @Override
            // Xem thông tin chi tiết sản phẩm
            public void SetItemClick(View view, int pos) {
                Intent intent = new Intent(context, Activity_DangSanPham.class);
                intent.putExtra("SP", sanPham);
                context.startActivityForResult(intent, 100);
            }
        });
        holder.txtbaohanh.setText(sanPham.getTrongluong());
        holder.txtsoluong.setText(sanPham.getSoluong() + "");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txttensp, txtgiasp, txtbaohanh, txtsoluong;
        ImageView hinhanh;
        InterSetOnItemClick itemClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgiasp = itemView.findViewById(R.id.txtgiatien);
            txttensp = itemView.findViewById(R.id.txttensp);
            hinhanh = itemView.findViewById(R.id.hinhanh);
            txtbaohanh = itemView.findViewById(R.id.txtbaohanh);
            txtsoluong = itemView.findViewById(R.id.txtsoluong);
            itemView.setOnClickListener(this);
        }

        public void SetOnItem(InterSetOnItemClick itemClick) {
            this.itemClick = itemClick;
        }

        @Override
        public void onClick(View v) {
            itemClick.SetItemClick(v, getAdapterPosition());
        }
    }
}
