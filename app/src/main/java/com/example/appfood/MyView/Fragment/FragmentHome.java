package com.example.appfood.MyView.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.appfood.MyAdapter.AdapterBanner;
import com.example.appfood.MyAdapter.AdapterSanPham;
import com.example.appfood.Model.SanPham;
import com.example.appfood.MyObject.PresenterSanPham;
import com.example.appfood.MyObject.InterSanPhamView;
import com.example.appfood.R;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements InterSanPhamView {
    View view;
    private ArrayList<String> arrayList;
    private ViewPager viewPager;
    private FirebaseFirestore db;
    private AdapterBanner adapterBanner;
    private PresenterSanPham presenterSanPham;
    private  ArrayList<SanPham> arr_sp,arr_sp_nb,arr_sp_tu,arr_sp_hq,arr_sp_mc,arr_sp_yt,arr_sp_lau,arr_sp_gy;
    private AdapterSanPham adapterSanPham,sanPhamNBAdapter,sanPhamTUAdapter,sanPhamHQAdapter,sanPhamMCAdapter,sanPhamYTAdapter,sanPhamLauAdapter,sanPhamGYAdapter;
    private RecyclerView rcvSP,rcvSpNoiBat,rcvSPThucUong,rcvSPHQ,rcvSPMC,rcvSPYT,rcvSPLau,rcvSPGY;
    private ImageButton imgBtnDanhMuc;

    FragMent_HomeListener activityCallback;
    public interface FragMent_HomeListener {
        void onButtonClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityCallback = (FragMent_HomeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " You must implement FirstFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        InitWidget();
        Init();
        InitSanPham();
        imgBtnDanhMuc.setOnClickListener(view ->{
            activityCallback.onButtonClick();
        });
        return  view;
    }

    private void InitWidget() {
        viewPager = view.findViewById(R.id.viewpager);
        rcvSP = view.findViewById(R.id.rcvSP);
        rcvSpNoiBat = view.findViewById(R.id.rcvNB);
        rcvSPThucUong = view.findViewById(R.id.rcvTU);
        rcvSPHQ = view.findViewById(R.id.rcvHQ);
        rcvSPMC = view.findViewById(R.id.rcvMC);
        rcvSPYT = view.findViewById(R.id.rcvYT);
        rcvSPLau = view.findViewById(R.id.rcvLau);
        rcvSPGY = view.findViewById(R.id.rcvGY);
        imgBtnDanhMuc = view .findViewById(R.id.home_danhmuc);
    }

    private void InitSanPham() {
        arr_sp = new ArrayList<>();
        arr_sp_nb = new ArrayList<>();
        arr_sp_tu = new ArrayList<>();
        arr_sp_hq = new ArrayList<>();
        arr_sp_mc = new ArrayList<>();
        arr_sp_yt = new ArrayList<>();
        arr_sp_lau = new ArrayList<>();
        arr_sp_gy = new ArrayList<>();
        presenterSanPham = new PresenterSanPham(this);
        presenterSanPham.HandlegetDataSanPham();
        presenterSanPham.HandlegetDataSanPhamNB();
        presenterSanPham.HandlegetDataSanPhamTU();
        presenterSanPham.HandlegetDataSanPhamHQ();
        presenterSanPham.HandlegetDataSanPhamMC();
        presenterSanPham.HandlegetDataSanPhamYT();
        presenterSanPham.HandlegetDataSanPhamLau();
        presenterSanPham.HandlegetDataSanPhamGY();
    }
    //Tạo banner
    private void Init() {
        arrayList = new ArrayList<>();
        db= FirebaseFirestore.getInstance();
        db.collection("Banner").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                 for(QueryDocumentSnapshot d : queryDocumentSnapshots){
                     arrayList.add(d.getString("hinhanh"));
                 }
                 adapterBanner = new AdapterBanner(getContext(),arrayList);
                 viewPager.setAdapter(adapterBanner);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    //Thời gian chuyển đổi banner
                    public void run() {
                        int k=viewPager.getCurrentItem();
                        if(k>=arrayList.size()-1){
                             k  = 0;
                        }else{
                            k++;
                        }
                        handler.postDelayed(this,3000);
                        viewPager.setCurrentItem(k,true);

                    }
                },3000);

            }
        });

    }


    @Override
    public void getDataSanPham(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                               String nhasanxuat, Long type,String trongluong) {
        arr_sp.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        adapterSanPham = new AdapterSanPham(getContext(),arr_sp);
        rcvSP.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSP.setAdapter(adapterSanPham);

    }

    @Override
    public void OnEmptyList() {

    }

    @Override
    public void getDataSanPhamNB(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_nb.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamNBAdapter = new AdapterSanPham(getContext(),arr_sp_nb,2);
        rcvSpNoiBat.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSpNoiBat.setAdapter(sanPhamNBAdapter);
    }
    @Override
    public void getDataSanPhamTU(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_tu.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamTUAdapter = new AdapterSanPham(getContext(),arr_sp_tu,3);
        rcvSPThucUong.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSPThucUong.setAdapter(sanPhamTUAdapter);
    }
    @Override
    public void getDataSanPhamHQ(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_hq.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamHQAdapter = new AdapterSanPham(getContext(),arr_sp_hq,4);
        rcvSPHQ.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSPHQ.setAdapter(sanPhamHQAdapter);
    }
    @Override
    public void getDataSanPhamMC(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_mc.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamMCAdapter = new AdapterSanPham(getContext(),arr_sp_mc,5);
        rcvSPMC.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSPMC.setAdapter(sanPhamMCAdapter);
    }
    @Override
    public void getDataSanPhamYT(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_yt.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamYTAdapter = new AdapterSanPham(getContext(),arr_sp_yt,6);
        rcvSPYT.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSPYT.setAdapter(sanPhamYTAdapter);
    }
    @Override
    public void getDataSanPhamLau(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_lau.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamLauAdapter = new AdapterSanPham(getContext(),arr_sp_lau,7);
        rcvSPLau.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvSPLau.setAdapter(sanPhamLauAdapter);
    }
    @Override
    public void getDataSanPhamGY(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String nhasanxuat, Long type, String trongluong) {
        arr_sp_gy.add(new SanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,nhasanxuat,type,trongluong));
        sanPhamGYAdapter = new AdapterSanPham(getContext(),arr_sp_gy,8);
        rcvSPGY.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rcvSPGY.setAdapter(sanPhamGYAdapter);
    }
}
