package com.example.appfood.MyObject;

import com.example.appfood.Model.GioHang;
import com.example.appfood.Model.SanPham;

import java.util.ArrayList;

public class PresenterGioHang implements InterGioHang {
    private GioHang gioHang;
    private InterGioHangView callback;

    public PresenterGioHang(InterGioHangView callback) {
        this.callback = callback;
        gioHang = new GioHang(this);
    }
    public  void AddCart(String idsp){
        gioHang.AddCart(idsp);
    }
    public  void  HandlegetDataGioHang(){
        gioHang.HandlegetDataGioHang();
    }
    public  void  HandlegetDataGioHang(String id){
        gioHang.HandlegetDataGioHang(id);
    }

    @Override
    public void OnSucess() {
        callback.OnSucess();
    }

    @Override
    public void OnFail() {
        callback.OnFail();
    }

    @Override
    public void getDataSanPham(String id, String idsp,String tensp, Long giatien, String hinhanh, String loaisp, Long soluong, String hansudung, Long type, String trongluong) {
        callback.getDataSanPham(id,idsp,tensp,giatien,hinhanh,loaisp,soluong,hansudung,type,trongluong);
    }

    public void HandleAddHoaDon(String ngaydat, String diachi, String hoten, String sdt, String phuongthuc, long tongtien, ArrayList<SanPham> arrayList) {
        gioHang.HandleThanhToan(ngaydat,diachi,hoten,sdt,phuongthuc,tongtien,arrayList);
    }

    public void HandlegetDataCTHD(String id) {
        gioHang.HandleGetDataCTHD(id);

    }
    public void HandlegetDataCTHD(String id,String uid) {
        gioHang.HandleGetDataCTHD(id,uid);

    }
}
