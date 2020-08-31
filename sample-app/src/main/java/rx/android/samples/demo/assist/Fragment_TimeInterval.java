package rx.android.samples.demo.assist;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.schedulers.TimeInterval;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_TimeInterval extends BaseFragment {

    public void runCode() {

        //将一个Observable转换为发射两个数据之间所耗费时间的Observable

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);
        values.take(3)
                .timeInterval()
                .subscribe(new Action1<TimeInterval>() {
                    @Override
                    public void call(TimeInterval mTimeInterval) {
                        println(mTimeInterval.toString());
                    }
                });

    }


}
