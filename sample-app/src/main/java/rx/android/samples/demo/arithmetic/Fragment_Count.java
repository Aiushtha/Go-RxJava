package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Count extends BaseFragment {


    public void runCode() {
        Observable.just(1, 2, 3).count().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                println("onnext:"+integer);

            }
        });

    }


}
