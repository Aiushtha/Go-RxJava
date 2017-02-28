package rx.android.samples.demo.create;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.samples.demo.BaseFragment;


/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Interval extends BaseFragment {

    private Subscription obs;
    public void runCode(){
        obs=Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
            @Override public void onCompleted() {
                println("onCompleted");
            }
            @Override public void onError(Throwable e) {
            }
            @Override public void onNext(Long aLong) {
                println("time : " + aLong);
            }
        });
    }
    //触发onPause 则停止计时
    @Override
    public void onPause() {
        super.onPause();
        obs.unsubscribe();
    }
}
