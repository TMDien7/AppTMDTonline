package com.example.appfood.MyObject;

public interface InterGioHang {
    void getDataSanPham(String id, String id_sp,String tensp, Long giatien, String hinhanh, String loaisp, Long soluong, String hansudung, Long type, String trongluong);
    void OnSucess();
    void OnFail();


}