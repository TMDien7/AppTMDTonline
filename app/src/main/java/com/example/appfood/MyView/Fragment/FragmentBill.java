package com.example.appfood.MyView.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.MyAdapter.AdapterHoaDonUser;
import com.example.appfood.Model.HoaDon;
import com.example.appfood.MyObject.PresenterHoaDon;
import com.example.appfood.MyObject.InterHoaDonView;
import com.example.appfood.R;
import com.example.appfood.MyView.HomeActivity;

import java.util.ArrayList;

public class FragmentBill extends Fragment  implements InterHoaDonView {
    View view;
    private RecyclerView rcvBill;
    private PresenterHoaDon presenterHoaDon;
    private AdapterHoaDonUser adapterHoaDonUser;
    private ArrayList<HoaDon> arrayList;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bill,container,false);

        rcvBill =view.findViewById(R.id.rcvBill);
        progressBar = view.findViewById(R.id.progressbar);
        presenterHoaDon = new PresenterHoaDon(this);
        arrayList = new ArrayList<>();

        presenterHoaDon.HandleReadDataHD();
        HomeActivity.countDownTimer = new CountDownTimer(1,1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                arrayList.clear();;
                if(adapterHoaDonUser !=null){
                    adapterHoaDonUser.notifyDataSetChanged();
                }
                presenterHoaDon.HandleReadDataHD();
                HomeActivity.countDownTimer.cancel();

            }
        };
        return  view;
    }

    @Override
    public void getDataHD(String id, String uid, String diachi, String hoten, String ngaydat, String phuongthuc, String sdt, Long tongtien,Long type) {
       arrayList.add(new HoaDon(id,uid,diachi,hoten,ngaydat,phuongthuc,sdt,tongtien,type));
       adapterHoaDonUser = new AdapterHoaDonUser(getContext(),arrayList);
       rcvBill.setLayoutManager(new LinearLayoutManager(getContext()));
       rcvBill.setAdapter(adapterHoaDonUser);
       progressBar.setVisibility(View.GONE);
    }


    @Override
    public void OnFail() {

    }

    @Override
    public void OnSucess() {

    }
}
