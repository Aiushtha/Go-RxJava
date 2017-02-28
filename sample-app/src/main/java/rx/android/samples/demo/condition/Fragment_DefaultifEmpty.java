package rx.android.samples.demo.condition;


import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DefaultifEmpty extends BaseFragment {

    public void runCode() {
        Observable.just(0,1)
                .defaultIfEmpty(0)
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
                    public void onNext(Integer i) {
                        println("-------->onNext()" + i);
                    }
                });












    }




}
