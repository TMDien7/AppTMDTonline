package com.example.appfood.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.appfood.MyObject.InterUser;

public class User {
    private FirebaseAuth firebaseAuth;
    private  String valid_email="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private  String email;
    private  String pass;
    private InterUser callback;

    public User(){

    }
    public User(InterUser callback){
        this.callback=callback;
        firebaseAuth = FirebaseAuth.getInstance();

    }
    // Đăng nhập
    public  void HandleLoginUser(String email,String pass){
        // Kiểm tra user đã đăng nhập email chưa
        if(email.length()>0){
             //Kiểm tra email có được hỗ trợ
            if(email.matches(valid_email)){
                if(pass.length()>0){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Kiểm tra user xác thực tài khoản
                            if(task.isSuccessful()){
                                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                    callback.OnSucess(); //Nếu user xác thực thì đăng nhập thành công
                                }else{
                                        // Nếu chưa thì User bắt buộc phải xác thực
                                    callback.OnAuthEmail();
                                }
                            }else{
                                callback.OnFail();
                            }
                        }
                    });
                }else{
                    callback.Onpass();
                }
                // Email chưa được hỗ trợ
            }else{
                callback.OnValidEmail();
            }
        }else{
            callback.OnLengthEmail();
        }
    }

    // Đăng kí
    public void HandleRegistUser(String email, String pass, String repass) {
        if(email.length()>0){
            if(email.matches(valid_email)){
                if(pass.length()>0){
                    if(pass.equals(repass)){
                        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    callback.OnAuthEmail();
                                }else{
                                    callback.OnFail();
                                }
                            }
                        });
                    }else{
                        callback.OnpassNotSame();
                    }
                }else{
                    callback.Onpass();
                }
            }else{
                callback.OnValidEmail();
            }
        }else{
            callback.OnLengthEmail();
        }
    }

}
