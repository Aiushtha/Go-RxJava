package rx.android.samples.demo.change;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Map extends BaseFragment {

    public void runCode() {
        Observable.range(0,8)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        if(integer<5) {
                            return integer.intValue() + 1;
                        }else
                        {
                            return null;
                        }
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println(integer);
            }
        });

    }


}
