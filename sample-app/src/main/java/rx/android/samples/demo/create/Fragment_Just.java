package rx.android.samples.demo.create;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;


/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Just extends BaseFragment {

    public void runCode() {
        Observable.just("1", "2")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s);
                    }
                });

    }


}
