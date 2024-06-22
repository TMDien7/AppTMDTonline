package com.example.appfood.MyView.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.example.appfood.MyObject.PresenterUser;
import com.example.appfood.MyObject.InterUserView;
import com.example.appfood.R;
import com.example.appfood.MyView.Admin.ActivitySignInAdmin;
import com.example.appfood.MyView.HomeActivity;

public class ActivitySignIn extends AppCompatActivity  implements InterUserView, View.OnClickListener {
    // Variables
    private Button btndangnhap;
    private EditText editemail,editpass;
    private PresenterUser presenterUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        InitWidget();
        Init();
    }

    private void InitWidget() {
        btndangnhap = findViewById(R.id.btndangnhap);
        editemail=findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
    }

    private void Init() {
        presenterUser = new PresenterUser(this);
        btndangnhap.setOnClickListener(this);
        findViewById(R.id.txtdangky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySignIn.this, ActivitySignUp.class));
            }
        });
        findViewById(R.id.txtadmin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ActivitySignIn.this, ActivitySignInAdmin.class));

            }
        });
    }


    @Override
    public void OnLengthEmail() {
        Toast.makeText(this, "Email không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnValidEmail() {
        Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPass() {
        Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnSucess() {
        startActivity(new Intent(this, HomeActivity.class));
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void OnAuthEmail() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
        Toast.makeText(this, "Vui lòng hãy vào gmail để xác thực!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this, "Sai tài khoản / Mật khẩu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPassNotSame() {
        Toast.makeText(this, "Tài khoản và mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btndangnhap:
                String email=editemail.getText().toString();
                String pass =editpass.getText().toString().trim();
                presenterUser.HandleLoginUser(email,pass);
        }
    }
}

