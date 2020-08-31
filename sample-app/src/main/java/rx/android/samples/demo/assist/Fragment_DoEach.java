package rx.android.samples.demo.assist;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoEach extends BaseFragment {

    public void runCode() {

//        do操作符就是给Observable的生命周期的各个阶段加上一系列的回调监听，当Observable执行到这个阶段的时候，这些回调就会被触发。在Rxjava实现了很多的doxxx操作符。
//        doOnEach可以给Observable加上这样的样一个回调：Observable每发射一个数据的时候就会触发这个回调，不仅包括onNext还包括onError和onCompleted。
        Observable.range(0, 4)
                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {

                        println("" + notification.getValue() + " " + notification.getKind());

                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("------>onError()" + e);
            }

            @Override
            public void onNext(Integer integer) {
                println("------->onNext()");
            }
        });
    }


}
