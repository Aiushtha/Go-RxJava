package rx.android.samples.demo.condition;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_All extends BaseFragment {

    public void runCode() {

        Observable.range(0, 10)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        println(integer);
                        if(integer<9)return true;
                        return false;
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                println("-------->onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("-------->onError()" + e);
            }

            @Override
            public void onNext(Boolean b) {
                println("-------->onNext()" + b);
            }
        });


    }


}
