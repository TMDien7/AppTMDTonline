package com.example.appfood.MyView.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.PresenterGioHang;
import com.example.appfood.MyObject.InterGioHangView;
import com.example.appfood.R;
import com.example.appfood.MyView.HomeActivity;

import java.text.NumberFormat;

public class

ActivityContentProduct extends AppCompatActivity implements InterGioHangView {
    private Intent intent;
    private SanPham sanPham;
    private TextView txttensp, txtgiatien, txtmota, txtnsx, txtbaohanh;
    private Toolbar toolbar;
    private ImageView hinhanh;
    private Button btndathang;
    private PresenterGioHang presenterGioHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_product);
        InitWidget();
        Init();
    }

    private void InitWidget() {
        toolbar = findViewById(R.id.toolbar);
        txtbaohanh = findViewById(R.id.txtbaohanh);
        txtgiatien = findViewById(R.id.txtgiatien);
        txtmota=findViewById(R.id.txtmota);
        txtnsx=findViewById(R.id.txtthuonghieu);
        txtbaohanh=findViewById(R.id.txtbaohanh);
        txttensp=findViewById(R.id.txttensp);
        hinhanh=findViewById(R.id.image_product);
        btndathang=findViewById(R.id.btndathang);
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent=getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("SP");
        txtnsx.setText("Bảo hành: "+ sanPham.getHansudung());
        txtmota.setText("Mô tả: "+ sanPham.getMota());
        txtbaohanh.setText("Trọng lượng: "+ sanPham.getTrongluong());
        txttensp.setText("Tên sản phẩm: "+ sanPham.getTensp());
        txtgiatien.setText("Giá tiền: "+NumberFormat.getNumberInstance().format(sanPham.getGiatien()));
        Picasso.get().load(sanPham.getHinhanh()).into(hinhanh);
        presenterGioHang = new PresenterGioHang(this);
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterGioHang.AddCart(sanPham.getId());
            }
        });

    }

    @Override
    public void OnSucess() {
        Toast.makeText(this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent( ActivityContentProduct.this, HomeActivity.class));
        finish();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this, "Thất Bại ! Lỗi hệ thống.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataSanPham(String id, String idsp, String tensp, Long giatien, String hinhanh, String loaisp, Long soluong, String hansudung, Long type, String trongluong) {

    }


}
