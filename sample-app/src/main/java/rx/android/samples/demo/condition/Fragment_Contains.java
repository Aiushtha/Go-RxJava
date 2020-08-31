package rx.android.samples.demo.condition;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Contains extends BaseFragment {

    public void runCode() {

        Observable.just(0,1,2)
                .contains(1)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Boolean i) {
                        println("-------->onNext()" + i);
                    }
                });

        Observable.just(0,1,2)
                .contains(-1)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Boolean i) {
                        println("-------->onNext()" + i);
                    }
                });

    }




}
