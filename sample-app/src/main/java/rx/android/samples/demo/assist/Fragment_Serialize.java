package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Serialize extends BaseFragment {

    public void runCode() {

//强制Observable按次序发射数据并且功能是有效的
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        })
                .cast(Integer.class)
                .serialize();
        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                println("Unsubscribed");
            }
        })
                .unsafeSubscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("Complete!");
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Integer integer) {
                    }
                });

    }


}
