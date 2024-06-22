package com.example.appfood.MyObject;

import com.example.appfood.Model.SanPham;

public class PresenterSanPham implements InterSanPham {
    private SanPham sanPham;
    private InterSanPhamView callback;
    public PresenterSanPham(InterSanPhamView callback){
        this.callback=callback;
        sanPham = new SanPham(this);

    }
    public void HandlegetDataSanPham(){
        sanPham.HandlegetDataSanPham();
    }
    public void HandlegetDataSanPhamTU(){
        sanPham.HandlegetDataSanPhamThucUong();
    }
    public void HandlegetDataSanPhamHQ(){
        sanPham.HandlegetDataSanPhamHanQuoc();
    }

    public void HandlegetDataSanPhamMC(){
        sanPham.HandlegetDataSanPhamMiCay();
    }
    public void HandlegetDataSanPhamYT(){
        sanPham.HandlegetDataSanPhamYeuThich();
    }
    public void HandlegetDataSanPhamLau(){
        sanPham.HandlegetDataSanPhamLau();
    }
    public void HandlegetDataSanPhamGY(){
        sanPham.HandlegetDataSanPhamGoiY();
    }
    @Override
    public void getDataSanPham(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                               String hansudung, Long type,String trongluong) {
       callback.getDataSanPham(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamTU(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                               String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamTU(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamHQ(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                                 String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamHQ(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamMC(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                                 String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamMC(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamYT(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                                 String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamYT(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamLau(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                                 String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamLau(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void getDataSanPhamGY(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong,
                                 String hansudung, Long type,String trongluong) {
        callback.getDataSanPhamGY(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }
    @Override
    public void OnEmptyList() {
        callback.OnEmptyList();
    }
// truyen dữ liệu qua màn hình
    @Override
    public void getDataSanPhamNB(String id, String tensp, Long giatien, String hinhanh, String loaisp, String mota, Long soluong, String hansudung, Long type, String trongluong) {
        callback.getDataSanPhamNB(id,tensp,giatien,hinhanh,loaisp,mota,soluong,hansudung,type,trongluong);
    }

    public void HandlegetDataSanPham(String loaisp,int type) {
        sanPham.HandlegetDataSanPham(loaisp,type);
    }


    public void HandlegetDataSanPhamNB() {
        sanPham.HandlegetDataSanPhamNoiBat();
    }

}
