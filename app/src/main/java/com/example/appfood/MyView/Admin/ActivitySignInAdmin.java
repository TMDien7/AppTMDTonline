package com.example.appfood.MyView.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.appfood.MyObject.PresenterUser;
import com.example.appfood.R;

public class ActivitySignInAdmin extends AppCompatActivity  implements  View.OnClickListener {
    private Button btndangnhap;
    private EditText editemail,editpass;
    private PresenterUser presenterUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);
        InitWidget();
        Init();
    }

    private void InitWidget() {
        btndangnhap = findViewById(R.id.btndangnhap);
        editemail=findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
    }

    private void Init() {
        btndangnhap.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btndangnhap:
                String username=editemail.getText().toString();
                String pass =editpass.getText().toString().trim();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                if(username.length()>0 ){
                    if(pass.length()>0){
                        // Lấy thông tin tài khoản trên admin
                        db.collection("Admin").
                                whereEqualTo("username",username)
                                .whereEqualTo("pass",pass).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                                if(queryDocumentSnapshots.size()>0){
                                    startActivity(new Intent(ActivitySignInAdmin.this, ActivityHomeAdmin.class));
                                    finish();
                                }else{
                                    Toast.makeText(ActivitySignInAdmin.this, "Sai tài khoản / Mật khẩu", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })     ;
                    }else{

                    }
                }else{

                }
        }
    }
}

