package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnRequest extends BaseFragment {

    public void runCode() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())  // 在doOnSubscribe()之前，不会影响上面的doOnSubscribe()
                .doOnRequest(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        println("doOnRequest:"+Thread.currentThread());
                    }
                })
                .subscribeOn(Schedulers.io()) // 会影响上面的doOnSubscribe()  .subscribe(new Observer<Integer>() {
                .observeOn(AndroidSchedulers.mainThread()) // 不会影响上面的doOnSubscribe()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(Thread.currentThread().getName());
                    }
                });


    }


}
