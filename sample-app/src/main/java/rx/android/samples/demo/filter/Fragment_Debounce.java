package rx.android.samples.demo.filter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Debounce extends BaseFragment {


    @Override
    protected void init() {
        super.init();
        println("试着连续点击");
    }

    Observable observable=
            Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    for (int i = 0; i < 10; i++) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(i);
                        }
                        int sleep = 100;
                        if (i % 3 == 0) {
                            sleep = 300;
                        }
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.newThread());

    public void runCode() {
        observable
                .debounce(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())// 对etKey[EditText]的监听操作 需要在主线程操作
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError()" + e);
                    }

                    @Override
                    public void onNext(Object obj) {
                        println("onNext()" + obj);
                    }
                });

    }


}