package rx.android.samples.demo.arithmetic;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Concat extends BaseFragment {

    public void runCode() {
       //可以将该操作符看作，依次将多个数据源释放到同一个地方
        Observable.concat(
                Observable.just(1),
                Observable.just(2),
                Observable.just(3)
        ).subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer i) {
                        println("onNext:" +i);
                    }
                });


    }


}
