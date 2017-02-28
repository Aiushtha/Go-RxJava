package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func2;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Reduce extends BaseFragment {

    public void runCode() {

        Observable.just(1,2,3,4).reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1*i2;
            }
        }) .subscribe(new Subscriber<Integer>() {
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
