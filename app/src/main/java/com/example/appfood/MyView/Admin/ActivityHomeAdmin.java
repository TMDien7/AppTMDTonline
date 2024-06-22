package com.example.appfood.MyView.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood.R;

public class ActivityHomeAdmin extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgdangsanpham;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        findViewById(R.id.cHoaDon).setOnClickListener(this);
        findViewById(R.id.cThongKe).setOnClickListener(this);
        findViewById(R.id.imgdangsanpham).setOnClickListener(this);
        findViewById(R.id.cSignOut).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cHoaDon: startActivity(new Intent( ActivityHomeAdmin.this, ActivityBillAdmin.class));break;
            case R.id.cThongKe: startActivity(new Intent( ActivityHomeAdmin.this, ActivityChartBill.class));break;
            case R.id.imgdangsanpham: startActivity(new Intent( ActivityHomeAdmin.this, ActivityAddProduct.class));break;
            case R.id.cSignOut: finish();break;
        }
    }

}
