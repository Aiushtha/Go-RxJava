package rx.android.samples.demo.assist;

import rx.Notification;
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

public class Fragment_Do extends BaseFragment {

    public void runCode() {

//        http:
//blog.chinaunix.net/uid-20771867-id-5206187.html
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())  // 在doOnSubscribe()之前，不会影响上面的doOnSubscribe()

                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {
                        println("doOnEach:" + Thread.currentThread().getName());
                    }
                }).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println("doOnNext:" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        println("doOnCompleted1:" + Thread.currentThread().getName());
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        println("doOnCompleted2:" + Thread.currentThread().getName());
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