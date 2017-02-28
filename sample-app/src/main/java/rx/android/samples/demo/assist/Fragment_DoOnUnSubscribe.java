package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Subscription;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnUnSubscribe extends BaseFragment {

    public void runCode() {

//        doOnUnSubscribe则会在Subscriber进行反订阅的时候触发回调。
//        当一个Observable通过OnError或者OnCompleted结束的时候，会反订阅所有的Subscriber。

        Observable observable = Observable.just(1, 2).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                println("I'm be unSubscribed!");
            }
        });
        Subscription subscribe1 = observable.subscribe();
        Subscription subscribe2 = observable.subscribe();
        subscribe1.unsubscribe();
        subscribe2.unsubscribe();

    }


}
