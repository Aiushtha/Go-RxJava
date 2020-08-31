package rx.android.samples.demo.convert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToSortedList extends BaseFragment {

    public void runCode() {
        Observable.just(2,4,1,3)
                .delaySubscription(1, TimeUnit.SECONDS)  //延迟5s订阅
                .toSortedList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        println("toSortedList onNext:" + integers);
                    }
                });

    }




}
