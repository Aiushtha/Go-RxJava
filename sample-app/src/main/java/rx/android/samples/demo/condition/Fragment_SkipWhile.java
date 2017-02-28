package rx.android.samples.demo.condition;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_SkipWhile extends BaseFragment {

    public void runCode() {
        Observable
                .interval(200, TimeUnit.MILLISECONDS)
                .skipWhile(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        if(aLong<=5)return true;
                        return false;
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                      println("onnext:"+aLong);
                    }
                });

    }




}
