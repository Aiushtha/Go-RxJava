package rx.android.samples.demo.filter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Sample extends BaseFragment {

   //http://blog.chinaunix.net/uid-20771867-id-5194384



    public void runCode() {
        Observable.range(0,10)
                .sample(1000, TimeUnit.MICROSECONDS)
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
                    public void onNext(Integer integer) {
                        println("-------->onNext()" + integer);
                    }
                });

    }


}
