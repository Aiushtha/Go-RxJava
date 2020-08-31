package rx.android.samples.demo.change;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_FlatMap extends BaseFragment {

    public void runCode() {
        Observable.range(0,8)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(final Integer integer) {
                        if(integer>5) {
                            return Observable.just(String.valueOf(integer));
                        }
                        return null;
                    }

                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s);
                    }
                });


    }


}
