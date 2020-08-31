package rx.android.samples.demo.create;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;


/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Create extends BaseFragment {

    public void runCode(){
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }

        }).subscribe(new Subscriber<Object>() {

            int sum;

            @Override
            public void onCompleted() {
                println(String.valueOf(sum));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                sum += (int) o;
                println(o.toString());
            }
        });

    }


}
