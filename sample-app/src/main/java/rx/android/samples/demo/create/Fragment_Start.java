package rx.android.samples.demo.create;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Start extends BaseFragment {

    public void runCode() {
        Observable observable = Observable.just("aa", "bb", "cc");
        observable
                .startWith("11", "22")
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        println("startWith-- " + o);
                        System.out.println("startWith-- " + o);
                    }
                });

    }


}
