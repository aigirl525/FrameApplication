package com.example.sqliteapplication.mvp.view.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteapplication.mvp.presenter.base.BasePresenter;


public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    public P presenter;
    //网络请求的抽象
    public ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd = new ProgressDialog(this);
        //点击空白处不消失
        pd.setCanceledOnTouchOutside(false);
        //initPresenter()是抽象方法，让view初始化自己的presenter
        presenter = initPresenter();
        //presenter和view绑定
        presenter.attach((V)this);
        //initActivity是抽象方法，让view完成自身各种控件的初始化
        initActivity(savedInstanceState);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }
    //实例化presenter
    public abstract P initPresenter();
    public abstract void initActivity(Bundle savedInstanceState);

}
