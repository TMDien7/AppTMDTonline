package com.example.appfood.MyObject;

public interface InterGioHangView {
    void getDataSanPham(String id, String idsp,String tensp, Long giatien, String hinhanh, String loaisp, Long soluong, String hansudung, Long type, String trongluong);
    void OnSucess();
    void OnFail();


}
