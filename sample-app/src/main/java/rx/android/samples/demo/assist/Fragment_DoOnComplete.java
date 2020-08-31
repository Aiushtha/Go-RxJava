package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnComplete extends BaseFragment {

    private Observable<Integer> observable;

    public void runCode() {

        //doOnComplete会在OnCompleted发生的时候触发回调。
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())  // 在doOnSubscribe()之前，不会影响上面的doOnSubscribe()
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        println(Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread()) // 会影响上面的doOnSubscribe()  .subscribe(new Observer<Integer>() {
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(Thread.currentThread().getName());
                    }
                });

    }


}
