package rx.android.samples.demo.create;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_From extends BaseFragment {

    public void runCode() {
        Integer[] arr = {1, 2, 3, 4};
        Observable.from(arr)
         .subscribe(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                println(o);
            }

        });

    }

}
