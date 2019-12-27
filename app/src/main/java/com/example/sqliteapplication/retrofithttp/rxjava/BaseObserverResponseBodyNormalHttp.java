package com.example.sqliteapplication.retrofithttp.rxjava;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ParseException;
import android.widget.Toast;

import com.example.sqliteapplication.retrofithttp.ERROR;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * http://blog.csdn.net/gesanri/article/details/52701651
 * 请求返回的实体类非常规类型时使用获取请求体文本的观察者  非常规类型指：不是BaseEntity<T>
 */

public abstract class BaseObserverResponseBodyNormalHttp implements Observer<ResponseBody> {
    private static final String TAG = "BaseObserverStringNormalHttp";
    private Context mContext;
    private Dialog mDialog;
    private Disposable mDisposable;
    private final int Service_Error_CODE = 100020;          //标识服务器返回错误的错误码

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public BaseObserverResponseBodyNormalHttp(Context context , Dialog dialog) {
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
    public void onNext(ResponseBody value) {
        if (value!=null) {
            onHandleSuccess(value);
        } else {
            onHandleError(Service_Error_CODE, "获取到的值为空");
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
                switch (httpException.code()) {
                    case UNAUTHORIZED:
                    case FORBIDDEN:
                    case NOT_FOUND:
                    case REQUEST_TIMEOUT:
                    case GATEWAY_TIMEOUT:
                    case INTERNAL_SERVER_ERROR:
                    case BAD_GATEWAY:
                    case SERVICE_UNAVAILABLE:
                    default:
                        onHandleError(httpException.code(), "HTTP错误");
                        break;
                }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            //均视为解析错误
            onHandleError(ERROR.PARSE_ERROR,"解析错误");
        }else if(e instanceof ConnectException){
            //均视为网络错误
            onHandleError(ERROR.NETWORD_ERROR, "网络错误");
        }
        else {
            //未知错误
            onHandleError(ERROR.UNKNOWN, "未知错误");
        }
    }

    @Override
    public void onComplete() {
        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
    // 一般情况下，成功是要单独处理，而失败只给用户提示就可以了，所以这里我将onHandleSuccess声明为抽象的，也就是子类必须要实现
    public abstract void onHandleSuccess(ResponseBody t);

    //而onHandleError不是抽象的，子类可以选择实现或就用默认的实现即可。
    public void onHandleError(int code, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

}