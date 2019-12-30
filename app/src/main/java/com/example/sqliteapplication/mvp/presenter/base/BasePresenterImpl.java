package com.example.sqliteapplication.mvp.presenter.base;


import android.app.Dialog;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenterImpl<V> implements BasePresenter<V> {
    private WeakReference<V> weakReferenceView;

    public Dialog pd;
    public ObservableTransformer<Observable, ObservableSource> composeFunction;
    private final long RETRY_TIMES = 1;
    private boolean showLoading = true;

    public void setPd(Dialog pd){
        this.pd = pd;
    }

    public BasePresenterImpl() {
        composeFunction = new ObservableTransformer<Observable, ObservableSource>() {
            @Override
            public ObservableSource apply(Observable observable){
                return observable.retry(RETRY_TIMES)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable){
                                    if (showLoading) {
                                        if(pd != null && !pd.isShowing()){
                                            pd.show();
                                        }
                                    }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    @Override
    public void attach(V mView)
    {
        this.weakReferenceView = new WeakReference<V>(mView);
    }

    @Override
    public void dettach() {
        if (isAttach()){
            weakReferenceView.clear();
            weakReferenceView = null;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public V obtainView(){
        return  isAttach() ? weakReferenceView.get() : null;
    }

    @Override
    public boolean isAttach(){
        return weakReferenceView != null && weakReferenceView.get() != null;
    }


}
