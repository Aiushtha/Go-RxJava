package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.observables.MathObservable;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Average extends BaseFragment {

    public void runCode() {
        //compile 'io.reactivex:rxjava-math:1.0.0'

//        http://blog.csdn.net/nicolelili1/article/details/52180643


        MathObservable.averageInteger(Observable.just(1, 2, 3, 4))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("onNext: " + integer);
                    }
                });


    }


}
