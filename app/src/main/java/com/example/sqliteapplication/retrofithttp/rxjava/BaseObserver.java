package com.example.sqliteapplication.retrofithttp.rxjava;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.sqliteapplication.retrofithttp.ERROR;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * http://blog.csdn.net/gesanri/article/details/52701651
 * 请求返回实体类正常的时候使用该观察者  如BaseEntity<T>   BaseEntity<User>
 */

public abstract class BaseObserver<T> implements Observer<BaseModel<T>> {
    private static final String TAG = "BaseObserverEntityNormalHttp";
    private Context mContext;
    private Dialog mDialog;
    private Disposable mDisposable;          //请求成功 服务器返回的错误码

    public BaseObserver(Context context , Dialog dialog) {
        mContext = context;
        mDialog = dialog;

        if(mDialog!=null) {
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mDisposable.dispose();
                }
            });
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(BaseModel<T> value) {
        if (value.isSuccess()) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getCode(), value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {

        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
        onHandleError(ERROR.UNKNOWN, "当前无网络，请稍后再试");
    }

    @Override
    public void onComplete() {
        if(mDialog != null && mDialog.isShowing()){
            try {
                mDialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    // 一般情况下，成功是要单独处理，而失败只给用户提示就可以了，所以这里我将onHandleSuccess声明为抽象的，也就是子类必须要实现
    public abstract void onHandleSuccess(T t);

    //而onHandleError不是抽象的，子类可以选择实现或就用默认的实现即可。
    public void onHandleError(int code, String message) {
        if(message!=null) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }
}