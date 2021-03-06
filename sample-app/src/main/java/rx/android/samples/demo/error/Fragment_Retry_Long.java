package rx.android.samples.demo.error;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Retry_Long extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203

    public void runCode() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(0);
                subscriber.onNext(1);
                //这里报错了
                subscriber.onError(new Throwable("do onError"));
                subscriber.onNext(2);
                subscriber.onNext(3);
            }
        }).retry(3)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Object obj) {
                        println("-------->onNext()" + obj);
                    }
                });

    }


}