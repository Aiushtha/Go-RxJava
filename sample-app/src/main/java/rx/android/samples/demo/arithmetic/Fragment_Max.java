package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.observables.MathObservable;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Max extends BaseFragment {

    public void runCode() {

        MathObservable.max(Observable.just(0, 1, 2, 4))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("onNext:"+integer);
                    }
                });


    }


}
