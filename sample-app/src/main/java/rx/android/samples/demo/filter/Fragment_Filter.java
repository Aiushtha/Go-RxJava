package rx.android.samples.demo.filter;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Filter extends BaseFragment {

    public void runCode() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Observable.from(
                arr)
                .filter(new Func1<Integer, Boolean>() {

                    @Override
                    public Boolean call(Integer integer) {
                        if (integer > 4) return true;
                        return false;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println(integer);
            }
        });

    }


}
