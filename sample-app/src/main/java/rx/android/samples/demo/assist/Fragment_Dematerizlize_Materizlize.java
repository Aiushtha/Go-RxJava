package rx.android.samples.demo.assist;

import rx.Notification;
import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Dematerizlize_Materizlize extends BaseFragment {

    private Observable<Notification<Integer>> meterializeObserver() {
        return Observable.range(0,4).materialize();
    }

    private Observable<Integer> deMeterializeObserver() {
        return meterializeObserver().dematerialize();
    }


    public void runCode() {
        meterializeObserver().subscribe(new Action1<Notification<Integer>>(){

            @Override
            public void call(Notification<Integer> notification) {
                println(notification.getKind()+" "+notification.getValue());
            }
        });
        deMeterializeObserver().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println(integer);
            }
        });

    }


}
