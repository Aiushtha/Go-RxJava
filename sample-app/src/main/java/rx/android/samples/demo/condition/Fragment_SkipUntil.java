package rx.android.samples.demo.condition;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_SkipUntil extends BaseFragment {

    public void runCode() {
        println("start");
        Observable
                .interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(2, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                      println("onnext:"+aLong);
                    }
                });

    }




}
