package com.example.sqliteapplication.mvp.v.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteapplication.Util.NetworkUtil;
import com.example.sqliteapplication.mvp.p.base.BasePresenter;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    public P presenter;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
    public abstract P initActivity(Bundle savedInstanceState);

}
