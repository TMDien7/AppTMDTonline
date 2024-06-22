package com.example.appfood.MyView.Bill;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.MyAdapter.AdapterSanPham;
import com.example.appfood.Model.HoaDon;
import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.PresenterGioHang;
import com.example.appfood.MyObject.InterGioHangView;
import com.example.appfood.MyObject.PresenterHoaDon;
import com.example.appfood.MyObject.InterHoaDonView;
import com.example.appfood.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ActivityContentBillAdmin extends AppCompatActivity implements InterGioHangView, InterHoaDonView {
    private Intent intent;
    private HoaDon hoaDon;
    private TextView txtmaHD, txthoten, txtdiachi, txtsdt, txttongtien,txtrangthai;
    private Toolbar toolbar;
    private ImageView hinhanh;
    private Button btncapnhat;
    private PresenterGioHang presenterGioHang;
    private ArrayList<SanPham> arrayList;
    private AdapterSanPham adapterSanPham;
    private RecyclerView rcvBill;
    private PresenterHoaDon presenterHoaDon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_bill);
        InitWidget();
        Init();
    }

    private void InitWidget() {
        toolbar = findViewById(R.id.toolbar);
        txtdiachi = findViewById(R.id.txtdiachi);
        txthoten = findViewById(R.id.txthoten);
        txtrangthai=findViewById(R.id.txtrangthaidonhang);
        txtsdt=findViewById(R.id.txtsdt);
        txttongtien=findViewById(R.id.txttongtien);
        txtmaHD=findViewById(R.id.txtmaHD);
        rcvBill=findViewById(R.id.rcvSP);
        btncapnhat=findViewById(R.id.btncapnhat);
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết hóa đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent=getIntent();
        hoaDon = (HoaDon) intent.getSerializableExtra("HD");
        int type = intent.getIntExtra("TYPE",0);
        txtdiachi.setText("Địa chỉ : "+ hoaDon.getDiachi());
        txtmaHD.setText("Mã HD :"+ hoaDon.getId());
        txthoten.setText("Họ tên : "+ hoaDon.getHoten());
        txtsdt.setText("Liên hệ : "+ hoaDon.getSdt());
        txttongtien.setText("Giá tiền: "+NumberFormat.getNumberInstance().format(hoaDon.getTongtien()));

        switch ((int) hoaDon.getType()){
            case  1: txtrangthai.setText("Trạng Thái : Đang xử lý");break;
            case  2: txtrangthai.setText("Trạng Thái : Đang giao hàng");break;
            case  3: txtrangthai.setText("Trạng Thái : Giao Hàng Thành Công");break;
            case  4: txtrangthai.setText("Trạng Thái : Hủy Đơn Hàng");break;
        }


        presenterGioHang = new PresenterGioHang(this);
        presenterHoaDon = new PresenterHoaDon(this);
        arrayList = new ArrayList<>();
        if(type == 5){
            presenterGioHang.HandlegetDataCTHD(hoaDon.getId(), hoaDon.getUid());
        }else{
            presenterGioHang.HandlegetDataCTHD(hoaDon.getId());
        }

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DiaLogUpDate();
            }
        });

    }
    //Hàm kiểm tra hủy đơn hàng
    private void DiaLogUpDate() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_trangthai);
        dialog.show();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Spinner spiner = dialog.findViewById(R.id.spinerCapNhat);
        String[] s = {"Chọn Mục","Đang xử lý","Đang giao hàng","Giao Hàng Thành Công","Hủy Đơn Hàng"} ;

        ArrayAdapter arrayAdapter  = new ArrayAdapter(this, android.R.layout.simple_list_item_1,s);
        spiner.setAdapter( arrayAdapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    if(hoaDon.getType() <3){
                        presenterHoaDon.CapNhatTrangThai(spiner.getSelectedItemPosition(), hoaDon.getId());
                        dialog.cancel();
                    }else if(hoaDon.getType() == 4){
                        Toast.makeText(ActivityContentBillAdmin.this, "Đơn hàng đã hủy!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ActivityContentBillAdmin.this, "Đơn hàng bạn không thể hủy", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void OnSucess() {
        Toast.makeText(this, "Cập nhật thành công ", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void OnFail() {
        Toast.makeText(this, "Thất Bại ! Lỗi hệ thống", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getDataSanPham(String id, String idsp, String tensp, Long giatien, String hinhanh, String loaisp, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,idsp,tensp,giatien,hinhanh,loaisp,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,1);
        rcvBill.setLayoutManager(new LinearLayoutManager(this));
        rcvBill.setAdapter(adapterSanPham);
    }

    @Override
    public void getDataHD(String id, String uid, String diachi, String hoten, String ngaydat, String phuongthuc, String sdt, Long tongtien, Long type) {

    }
}
