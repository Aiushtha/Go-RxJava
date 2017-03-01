package rx.android.samples.demo.skill;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Com extends BaseFragment {


    @Override
    protected void init() {
        super.init();
        Observable.just(1)
                .compose(new Observable.Transformer<Integer, Integer>() {
                    @Override
                    public Observable<Integer> call(Observable<Integer> integerObservable) {
                        integerObservable.timeout(200, TimeUnit.MILLISECONDS);
                        return integerObservable;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                println("onnext:"+e);
            }

            @Override
            public void onNext(Integer integer) {
                println("onnext:"+integer);
            }
        });

    }






}
