package com.example.appfood.MyAdapter;

import android.content.Context;
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

import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHodler> {
    // Variables
    private Context context;
    private ArrayList<SanPham> arrayList;
    private  int type = 0;

    public AdapterGioHang(Context context, ArrayList<SanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public AdapterGioHang(Context context, ArrayList<SanPham> arrayList, int type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type= type;
    }

    @NonNull
    @Override
    public AdapterGioHang.ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type==0){
             view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        }else if(type ==2){
            view = LayoutInflater.from(context).inflate(R.layout.item_sanpham_noibat,parent,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item_giohang,parent,false);
        }
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGioHang.ViewHodler holder, int position) {

        SanPham sanPham = arrayList.get(position);
        holder.txttensp.setText(sanPham.getTensp());
        holder.txtgiasp.setText(NumberFormat.getInstance().format(sanPham.getGiatien())+" Đ");

        //  Tải hình ảnh vào ImageView
        Picasso.get().load(sanPham.getHinhanh()).into(holder.hinhanh);

        holder.SetOnItem(new InterSetOnItemClick() {
            // Xem thông tin chi tiết sản phẩm
            @Override
            public void SetItemClick(View view, int pos) {

            }
        });
        if(type==1){
            holder.txtbaohanh.setText(sanPham.getTrongluong());
            holder.txtsoluong.setText(sanPham.getSoluong()+"");
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    // View Holder
    public class ViewHodler extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView txttensp,txtgiasp,txtbaohanh,txtsoluong;
        ImageView hinhanh;
        InterSetOnItemClick itemClick;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);

            txtgiasp= itemView.findViewById(R.id.txtgiatien);
            txttensp= itemView.findViewById(R.id.txttensp);
            hinhanh= itemView.findViewById(R.id.hinhanh);


            if(type==1){
                txtbaohanh = itemView.findViewById(R.id.txtbaohanh);
                txtsoluong = itemView.findViewById(R.id.txtsoluong);
            }
            itemView.setOnClickListener(this);
        }

        public  void  SetOnItem(InterSetOnItemClick itemClick){
            this.itemClick = itemClick;
        }
        @Override
        public void onClick(View v) {
            itemClick.SetItemClick(v,getAdapterPosition());
        }
    }
}
