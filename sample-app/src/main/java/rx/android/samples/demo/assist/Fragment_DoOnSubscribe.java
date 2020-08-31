package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnSubscribe extends BaseFragment {

    public void runCode() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())  // 在doOnSubscribe()之前，不会影响上面的doOnSubscribe()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        println(Thread.currentThread().getName()); // 在子线程中执行
                    }
                }).subscribeOn(Schedulers.io()) // 会影响上面的doOnSubscribe()  .subscribe(new Observer<Integer>() {
                .subscribeOn(AndroidSchedulers.mainThread()) // 不会影响上面的doOnSubscribe()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(Thread.currentThread().getName());
                    }
                });


    }


}
