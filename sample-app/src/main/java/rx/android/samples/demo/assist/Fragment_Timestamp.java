package rx.android.samples.demo.assist;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.schedulers.Timestamped;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Timestamp extends BaseFragment {

    public void runCode() {

        Observable
                .interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .timestamp()
                .subscribe(new Observer<Timestamped>() {
            @Override
            public void onCompleted() {
                println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("------>onError()" + e);
            }

            @Override
            public void onNext(Timestamped mTimestamped) {
                println("------->onNext()"+mTimestamped);
            }
        });

    }


}
