package com.example.appfood.MyView.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood.MyView.Admin.ActivitySignInAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.example.appfood.MyObject.PresenterUser;
import com.example.appfood.MyObject.InterUserView;
import com.example.appfood.R;
import com.example.appfood.MyView.HomeActivity;

public class ActivitySignUp extends AppCompatActivity  implements InterUserView, View.OnClickListener {
    private Button btndangky;
    private EditText editemail,editpass,editpass_repeat;
    private TextView textView_back;
    private PresenterUser presenterUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitWidget();
        Init();
    }

    private void InitWidget() {
        btndangky = findViewById(R.id.btndangky);
        editemail=findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
        editpass_repeat = findViewById(R.id.editmatkhau_repeat);
        textView_back = findViewById(R.id.btn_back);

    }

    private void Init() {
        presenterUser = new PresenterUser(this);
        btndangky.setOnClickListener(this);

        textView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ActivitySignUp.this, ActivitySignIn.class));
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
    public void OnPassNotSame() {
        Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSucess() {
        startActivity(new Intent(this, HomeActivity.class));
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void OnAuthEmail() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

        Toast.makeText(this, "Vui lòng hãy vào gamil để xác thực tài khoản của bạn !", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this, "Tài khoản đã được đăng ký !", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btndangky:
                String email=editemail.getText().toString();
                String pass =editpass.getText().toString().trim();
                String repass =editpass_repeat.getText().toString().trim();
                presenterUser.HandleRegist(email,pass,repass);
        }
    }
}

