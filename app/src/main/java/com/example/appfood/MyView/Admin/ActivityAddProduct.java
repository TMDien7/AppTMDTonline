package com.example.appfood.MyView.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.InterSanPham;
import com.example.appfood.R;
import com.example.appfood.Activity_DangSanPham;

import java.util.ArrayList;

public class ActivityAddProduct extends AppCompatActivity {
    AdapterProduct adapter;
    RecyclerView rcv;
    ImageView back_admin;
    private SanPham sanPham;
    private ArrayList<SanPham> arr_sp = new ArrayList<>();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_admin);

        rcv = findViewById(R.id.rcv);
        back_admin = findViewById(R.id.btn_back_add_admin);
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        back_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityAddProduct.this, ActivityHomeAdmin.class));
            }
        });


        sanPham = new SanPham(new InterSanPham() {
            @Override
            public void getDataSanPham(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void OnEmptyList() {
                dialog.dismiss();
            }


            @Override
            public void getDataSanPhamNB(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }


            @Override
            public void getDataSanPhamTU(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void getDataSanPhamHQ(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void getDataSanPhamMC(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void getDataSanPhamYT(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void getDataSanPhamLau(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void getDataSanPhamGY(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
                arr_sp.add(new SanPham(id, tensp, giatien, hinhanh, loaisp, mota, soluong, nhasanxuat, type, trongluong));
                adapter = new AdapterProduct(ActivityAddProduct.this, arr_sp);
                rcv.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        dialog.show();
        sanPham.HandlegetDataSanPhamAll();
        findViewById(R.id.image_add).setOnClickListener(view -> {
            startActivityForResult(new Intent(ActivityAddProduct.this, Activity_DangSanPham.class), 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                dialog.show();
                arr_sp.clear();
                sanPham.HandlegetDataSanPhamAll();

            }
        }
    }
}