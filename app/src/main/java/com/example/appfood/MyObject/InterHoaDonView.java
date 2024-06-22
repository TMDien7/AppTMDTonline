package com.example.appfood.MyObject;

public interface InterHoaDonView {
    void getDataHD(String id, String uid, String diachi, String hoten, String ngaydat, String phuongthuc, String sdt, Long tongtien,Long type);
    void OnFail();
    void OnSucess();
}
