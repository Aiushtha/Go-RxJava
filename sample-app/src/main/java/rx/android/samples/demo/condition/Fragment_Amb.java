package rx.android.samples.demo.condition;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Amb extends BaseFragment {

    public void runCode() {
        Observable.amb(
                Observable.just(1,2,3).delay(2, TimeUnit.SECONDS),
                Observable.just(4,5,6).delay(3, TimeUnit.SECONDS),
                Observable.just(7,8,9).delay(1, TimeUnit.SECONDS)
               )
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Integer i) {
                        println("-------->onNext()" + i);
                    }
                });

    }




}
