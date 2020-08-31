package rx.android.samples.demo.assist;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Subscribe extends BaseFragment {

    public void runCode() {

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Observable.from(arr)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(final Integer integer) {
                        return Observable.just(String.valueOf(integer));
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
