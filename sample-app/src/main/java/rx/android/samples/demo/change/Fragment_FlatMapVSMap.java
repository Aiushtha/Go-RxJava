package rx.android.samples.demo.change;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_FlatMapVSMap extends BaseFragment {

    public void runCode() {
        Observable.range(1,3)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(final Integer integer) {

                        switch (integer) {
                            case 1:
                                return newObservable(integer).subscribeOn(AndroidSchedulers.mainThread());

                            case 2:
                                return newObservable(integer).subscribeOn(Schedulers.io());

                            case 3:
                                return newObservable(integer).subscribeOn(Schedulers.newThread());

                        }
                        return null;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s + " " + Thread.currentThread());
                    }
                });


    }

    public Observable newObservable(final Integer integer) {
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(String.valueOf(integer));
            }
        });
    }


}
