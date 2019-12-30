package com.example.sqliteapplication.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.example.sqliteapplication.mvp.model.OfficeModel;
import com.example.sqliteapplication.mvp.model.OfficeModelImpl;
import com.example.sqliteapplication.mvp.presenter.base.BasePresenterImpl;
import com.example.sqliteapplication.mvp.view.LoginView;
import com.example.sqliteapplication.retrofithttp.rxjava.BaseObserver;
import com.example.sqliteapplication.retrofithttp.rxjava.BaseObserverResponseBodyNormalHttp;
import com.example.sqliteapplication.retrofithttp.rxjava.User;

import okhttp3.ResponseBody;

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter<LoginView> {

    private OfficeModel officeModel;
    private Context context;

    private LoginPresenterImpl() {
    }

    public LoginPresenterImpl(Context context) {
        this(context,null);
    }

    private LoginPresenterImpl(Context context,Dialog pd) {
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
    public void getService(String type, String version) {
        officeModel.getService(type,version).compose(composeFunction)
                .subscribe(new BaseObserverResponseBodyNormalHttp(context,pd){

                    @Override
                    public void onHandleSuccess(ResponseBody t) {
                        obtainView().showGetServiceSucess();
                    }
                }  );
    }

    @Override
    public void attach(LoginView mView) {
        super.attach(mView);
    }
}
