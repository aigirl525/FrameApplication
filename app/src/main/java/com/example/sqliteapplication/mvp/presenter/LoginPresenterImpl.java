package com.example.sqliteapplication.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.example.sqliteapplication.mvp.VersionBean;
import com.example.sqliteapplication.mvp.model.OfficeModel;
import com.example.sqliteapplication.mvp.model.OfficeModelImpl;
import com.example.sqliteapplication.mvp.presenter.base.BasePresenterImpl;
import com.example.sqliteapplication.mvp.view.LoginView;
import com.example.sqliteapplication.retrofithttp.rxjava.BaseModel;
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
    public void queryVersion(Dialog dialog,String type) {
        setPd(dialog);
        String url = "https://gddev.highlight2018.com/webchat_gongan_api/lyapi/queryVersion.do";
        officeModel.queryVersion(url,type).compose(composeFunction)
                .subscribe(new BaseObserver<VersionBean>(context,pd) {
                    @Override
                    public void onHandleSuccess(VersionBean versionBean) {
                        obtainView().showVersionBean(versionBean);
                    }

                });

    }

    @Override
    public void getService(Dialog dialog,String type, String version) {
        setPd(dialog);
        officeModel.getService(type,version).compose(composeFunction)
                .subscribe(new BaseObserverResponseBodyNormalHttp(context,pd){

                    @Override
                    public void onHandleSuccess(ResponseBody t) {
                        try{
                            obtainView().showGetServiceSucess(t.string());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }  );
    }

    @Override
    public void attach(LoginView mView) {
        super.attach(mView);
    }
}
