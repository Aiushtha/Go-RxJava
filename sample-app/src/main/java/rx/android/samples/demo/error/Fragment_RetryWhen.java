package rx.android.samples.demo.error;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_RetryWhen extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203


//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_submit.setText("运行一下,试着连续点击");
//
//    }

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
        }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return Observable.timer(2000, TimeUnit.MILLISECONDS);
            }
        })
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
                        println("onNext:" + obj + " " + Thread.currentThread());
                    }
        });

    }
}
