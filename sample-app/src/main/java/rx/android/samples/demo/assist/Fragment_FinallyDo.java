package rx.android.samples.demo.assist;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_FinallyDo extends BaseFragment {

    public void runCode() {

//        finallyDo
//
//        finallyDo会在Observable结束后触发回调，无论是正常还是异常终止。

        Observable observable = Observable.empty().finallyDo(new Action0() {
            @Override
            public void call() {
                println("already terminate");
            }
        });
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                println("throwable!");
            }
        }, new Action0() {
            @Override
            public void call() {
                println("Complete!");
            }
        });
        
    }


}
