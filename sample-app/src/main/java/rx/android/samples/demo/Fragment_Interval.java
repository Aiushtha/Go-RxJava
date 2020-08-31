package rx.android.samples.demo;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Interval extends BaseFragment {

    private Subscription sub;

    public void runCode() {
        sub =
                Observable.empty()
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        println("subscribe is pause");
                    }
                })
                .interval(1, TimeUnit.SECONDS, Schedulers.newThread())

                //https://github.com/trello/RxLifecycle
                //.compose(this.<Long>bindUntilEvent(ActivityEvent.STOP ))   //当Activity执行Onstop()方法是解除订阅关系
                .subscribe(new Observable.OnSubscribe() {
                    @Override
                    public void call(Object o) {
                        println("subscribe:" + System.currentTimeMillis() + " " + Thread.currentThread());
                    }
                });


    }

    @Override
    public void onPause() {
        super.onPause();
        sub.unsubscribe();

    }


}
