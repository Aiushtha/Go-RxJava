package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.observables.MathObservable;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Sum extends BaseFragment {

    public void runCode() {

        MathObservable.sumInteger(Observable.just(11, 2, 3, 4, 5))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer v) {
                        println( "onNext:." + v);

                    }

                    @Override
                    public void onCompleted() {
                        println("onCompleted:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError:");
                    }
                });



    }


}
