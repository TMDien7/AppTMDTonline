package com.example.appfood.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.Model.HoaDon;
import com.example.appfood.MyObject.InterSetOnItemClick;

import com.example.appfood.MyView.Bill.ActivityContentBillAdmin;
import com.example.appfood.R;


import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterHoaDonAdmin extends RecyclerView.Adapter<AdapterHoaDonAdmin.ViewHodler> {
    private Context context;
    private ArrayList<HoaDon> arrayList;
    private  int type = 0;

    public AdapterHoaDonAdmin(Context context, ArrayList<HoaDon> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public AdapterHoaDonAdmin(Context context, ArrayList<HoaDon> arrayList, int type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type= type;
    }

    @NonNull
    @Override
    public AdapterHoaDonAdmin.ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type==0 || type==5){
             view = LayoutInflater.from(context).inflate(R.layout.item_hoadon,parent,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item_giohang,parent,false);
        }


        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHoaDonAdmin.ViewHodler holder, int position) {

        HoaDon sanPhamModels = arrayList.get(position);

        holder.txthoten.setText("Họ tên : "+sanPhamModels.getHoten());
        holder.txtdiachi.setText("Địa chỉ : "+sanPhamModels.getDiachi());
        holder.txtsdt.setText("Số điện thoại : "+sanPhamModels.getSdt());
        holder.txttongtien.setText("Tổng tiền :"+NumberFormat.getInstance().format(sanPhamModels.getTongtien()) +" Đ");
        holder.txtngaydat.setText(sanPhamModels.getNgaydat());

        holder.SetOnItem(new InterSetOnItemClick() {
            @Override
            public void SetItemClick(View view, int pos) {
                Intent intent = new Intent(context, ActivityContentBillAdmin.class);
                intent.putExtra("HD",sanPhamModels);
                intent.putExtra("TYPE",type);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView txthoten,txtsdt,txtdiachi,txtngaydat,txttongtien;
        InterSetOnItemClick itemClick;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            txthoten= itemView.findViewById(R.id.txthoten);
            txtsdt= itemView.findViewById(R.id.txtsdt);
            txtdiachi= itemView.findViewById(R.id.txtdiachi);
            txtngaydat= itemView.findViewById(R.id.txtngaydat);
            txttongtien= itemView.findViewById(R.id.txttongtien);

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
