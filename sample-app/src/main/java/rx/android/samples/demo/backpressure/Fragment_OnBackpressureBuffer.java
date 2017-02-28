package rx.android.samples.demo.backpressure;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2017/2/14.
 */

public class Fragment_OnBackpressureBuffer extends BaseFragment {


    @Override
    public void runCode() {
        super.runCode();
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer(1000)
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        println("onNext: " + aLong);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
