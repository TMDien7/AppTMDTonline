package com.example.appfood.MyObject;

import com.example.appfood.Model.User;

public class PresenterUser implements InterUser {
    private User user;
    private InterUserView callback;

    public PresenterUser(InterUserView callback) {
        this.callback = callback;
         user = new User(this);
    }
    public  void HandleLoginUser(String email,String pass){
        user.HandleLoginUser(email,pass);
    }
    @Override
    public void OnLengthEmail() {
         callback.OnLengthEmail();
    }
    @Override
    public void OnValidEmail() {
       callback.OnValidEmail();
    }
    @Override
    public void Onpass() {
        callback.OnPass();
    }

    @Override
    public void OnSucess() {
        callback.OnSucess();
    }

    @Override
    public void OnAuthEmail() {
        callback.OnAuthEmail();
    }

    @Override
    public void OnFail() {
        callback.OnFail();
    }

    @Override
    public void OnpassNotSame() {
        callback.OnPassNotSame();
    }

    public void HandleRegist(String email, String pass, String repass) {
        user.HandleRegistUser(email,pass,repass);
    }
}
