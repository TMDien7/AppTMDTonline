package com.example.appfood.MyObject;

import com.example.appfood.Model.HoaDon;

public class PresenterHoaDon implements InterHoaDon {

    private HoaDon hoaDon;
    private InterHoaDonView callback;

    public PresenterHoaDon(InterHoaDonView callback) {
        this.callback = callback;
        hoaDon = new HoaDon(this);
    }
    public  void HandleReadDataHD(){
        hoaDon.HandleReadData();
    }

    @Override
    public void getDataHD(String id, String uid, String diachi, String hoten, String ngaydat, String phuongthuc, String sdt, Long tongtien,Long type) {
        callback.getDataHD(id,uid,diachi,hoten,ngaydat,phuongthuc,sdt,tongtien,type);
    }

    @Override
    public void OnSucess() {
        callback.OnSucess();

    }

    @Override
    public void OnFail() {
      callback.OnFail();
    }

    public void CapNhatTrangThai(int i,String id) {
        hoaDon.HandleUpdateStatusBill(i,id);
    }

    public void HandleReadDataHD(int position) {
        hoaDon.HandleReadData(position);
    }
}
