package com.example.sqliteapplication.mvp.p;

import android.app.Dialog;
import android.content.Context;

import com.example.sqliteapplication.mvp.m.OfficeModel;
import com.example.sqliteapplication.mvp.m.OfficeModelImpl;
import com.example.sqliteapplication.mvp.p.base.BasePresenterImpl;
import com.example.sqliteapplication.mvp.v.LoginView;
import com.example.sqliteapplication.retrofithttp.rxjava.BaseObserver;
import com.example.sqliteapplication.retrofithttp.rxjava.User;

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter<LoginView> {

    private OfficeModel officeModel;
    private Context context;

    public LoginPresenterImpl(Context context) {
        this(context,null);
    }

    public LoginPresenterImpl(Context context,Dialog pd) {
        setPd(pd);
        this.context = context;
        this.officeModel =  new OfficeModelImpl();
    }

    @Override
    public void login(String username, String password) {
        officeModel.login(username,password).compose(composeFunction)
                .subscribe(new BaseObserver<User>(context,pd) {
                    @Override
                    public void onHandleSuccess(User o) {
                        obtainView().showLoginDialog();
                    }

                });

    }

    @Override
    public void attach(LoginView mView) {
        super.attach(mView);
    }
}
