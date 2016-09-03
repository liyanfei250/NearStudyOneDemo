package net;


import android.content.Context;

import rx.Subscriber;
import util.ToastUtils;


/**
 * 通用订阅者,用于统一处理错误回调
 */
public class SimpleSubscriber<T> extends Subscriber<T> {

    private Context context;

    public SimpleSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onCompleted() {
        // sub
    }

    @Override
    public void onError(Throwable throwable) {
//        String errorInfo = ErrorInfoUtils.parseHttpErrorInfo(throwable);
        ToastUtils.showToast(context, throwable.toString());
    }

    @Override
    public void onNext(T t) {
        // sub
    }


}
