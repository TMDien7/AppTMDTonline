package com.example.appfood.MyView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.appfood.MyAdapter.AdapterSanPham;
import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.PresenterSanPham;
import com.example.appfood.MyObject.InterSanPhamView;
import com.example.appfood.R;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDanhMucActivity  extends AppCompatActivity implements InterSanPhamView {
    private Spinner spinerthongke;
    private FirebaseFirestore db;
    private Toolbar toolbar;
    private List<String> list;
    private  ArrayList<SanPham> arrayList;
    private PresenterSanPham presenterSanPham;
    private RecyclerView rCvSP;
    private AdapterSanPham adapterSanPham;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_danhmuc);
        InitWidget();
        Init();
    }

    private void Init() {
        Intent intent = getIntent();



       presenterSanPham = new PresenterSanPham(this);
       arrayList  =new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        db= FirebaseFirestore.getInstance();
        list.add("Chọn Danh Mục");
        db.collection("LoaiSP").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
               for(QueryDocumentSnapshot q :  queryDocumentSnapshots){
                   list.add(q.getString("tenloai"));
               }
                ArrayAdapter arrayAdapter = new ArrayAdapter(ThongKeDanhMucActivity.this, android.R.layout.simple_list_item_1,list);
               spinerthongke.setAdapter(arrayAdapter);

            }
        });

        if(intent.hasExtra("KEY")){ arrayList.clear();
            if(adapterSanPham !=null){
                adapterSanPham.notifyDataSetChanged();
            }
            String key = intent.getStringExtra("KEY");
          presenterSanPham.HandlegetDataSanPham(key,2);
        }
        spinerthongke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position>0){
                        arrayList.clear();
                        if(adapterSanPham !=null){
                            adapterSanPham.notifyDataSetChanged();
                        }
                        presenterSanPham.HandlegetDataSanPham(spinerthongke.getSelectedItem().toString(),1);

                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void InitWidget() {
        spinerthongke = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toolbar);
        rCvSP = findViewById(R.id.rcvDanhMuc);
    }

    @Override
    public void getDataSanPham(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
       arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
       adapterSanPham = new AdapterSanPham(this,arrayList,1);
       rCvSP.setLayoutManager(new LinearLayoutManager(this));
       rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamTU(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,3);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamHQ(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,4);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamMC(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,5);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamYT(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,6);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamLau(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,7);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void getDataSanPhamGY(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        arrayList.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong));
        adapterSanPham = new AdapterSanPham(this,arrayList,8);
        rCvSP.setLayoutManager(new LinearLayoutManager(this));
        rCvSP.setAdapter(adapterSanPham);


    }
    @Override
    public void OnEmptyList() {
        Toast.makeText(this, "Không tìm thấy sản phẩm nào trong danh mục : "+spinerthongke.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataSanPhamNB(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {

    }

}
