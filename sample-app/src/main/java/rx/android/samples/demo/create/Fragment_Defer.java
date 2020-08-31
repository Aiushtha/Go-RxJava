package rx.android.samples.demo.create;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func0;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Defer extends BaseFragment {

    public void runCode() {
        Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable call() {
                println("on defer");
                return Observable.just(0);
            }
        }).subscribe(new Subscriber<Object>() {

            @Override
            public void onCompleted() {
                    println("onCompleted");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                println("onNext:"+o);
            }
        });


    }

}
