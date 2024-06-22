package com.example.appfood.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.appfood.MyObject.InterHoaDon;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private  String id;
    private  String uid;
    private  String diachi;
    private  String hoten;
    private  String ngaydat;
    private  String phuongthuc;
    private  String sdt;
    private  long tongtien;
    private  long type;
    private InterHoaDon callback;
    private FirebaseFirestore db;

    // When using firebase, always create empty constructor
    public HoaDon() {

    }

    public HoaDon(String id, String uid, String diachi, String hoten, String ngaydat, String phuongthuc, String sdt, long tongtien, long type) {
        this.id = id;
        this.uid = uid;
        this.diachi = diachi;
        this.hoten = hoten;
        this.ngaydat = ngaydat;
        this.phuongthuc = phuongthuc;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.type=type;
    }

    public long getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(String phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }


    public HoaDon(InterHoaDon callback) {
        this.callback=callback;
        db=FirebaseFirestore.getInstance();
    }

    public  void HandleReadData(){
        db.collection("HoaDon").whereEqualTo("UID",FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.size()>0){
                    for(QueryDocumentSnapshot d : queryDocumentSnapshots){
                        callback.getDataHD(d.getId(),d.getString("UID"),d.getString("diachi"),
                                d.getString("hoten"),d.getString("ngaydat"),d.getString("phuongthuc"),d.getString("sdt"),
                                d.getLong("tongtien"),d.getLong("trangthai"));
                    }
                }

            }
        });
    }

    // Cập nhật trạng thái
    public void HandleUpdateStatusBill(int i,String id) {
        db.collection("HoaDon")
                .document(id).update("trangthai",i).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callback.OnSucess();
                }else{
                    callback.OnFail();
                }
            }
        });
    }

    public void HandleReadData(int position) {
        if(position==0){
            db.collection("HoaDon")
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                    if(queryDocumentSnapshots.size()>0){
                        for(QueryDocumentSnapshot d : queryDocumentSnapshots){
                            callback.getDataHD(d.getId(),d.getString("UID"),d.getString("diachi"),
                                    d.getString("hoten"),d.getString("ngaydat"),d.getString("phuongthuc"),d.getString("sdt"),
                                    d.getLong("tongtien"),d.getLong("trangthai"));
                        }
                    }

                }
            });
        }else{
            db.collection("HoaDon").whereEqualTo("trangthai",position)
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                    if(queryDocumentSnapshots.size()>0){
                        for(QueryDocumentSnapshot d : queryDocumentSnapshots){
                            callback.getDataHD(d.getId(),d.getString("UID"),d.getString("diachi"),
                                    d.getString("hoten"),d.getString("ngaydat"),d.getString("phuongthuc"),d.getString("sdt"),
                                    d.getLong("tongtien"),d.getLong("trangthai"));
                        }
                    }
                }
            });
        }
    }
}
