package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnTerminate extends BaseFragment {

    public void runCode() {

//        DoOnTerminate会在Observable结束前触发回调，无论是正常还是异常终止；
        Observable.range(0,4).doOnTerminate(new Action0() {
            @Override
            public void call() {
                println("------>doOnTerminate()");
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
                println("------->onNext()");
            }
        });
        
    }


}
