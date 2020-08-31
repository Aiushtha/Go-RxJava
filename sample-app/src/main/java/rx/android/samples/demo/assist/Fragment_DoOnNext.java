package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnNext extends BaseFragment {

    public void runCode() {
        Observable.range(0,4).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println("------>doOnNext()"+integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("------>onError()" + e);
            }

            @Override
            public void onNext(Integer integer) {
                println("------->onNext()"+integer);
            }
        });

    }


}
