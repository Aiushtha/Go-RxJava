package rx.android.samples.demo.change;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_GroupBy extends BaseFragment {

    public void runCode() {
        Observable.range(0, 9).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer % 3;
            }
        }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {
             println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                println("onError");
            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> childObservable) {
                println("child onNext");
                childObservable.subscribe(new Observer<Integer>() {
                    int k;
                    @Override
                    public void onCompleted() {
                        println("Grouped onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("Grouped onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("Grouped id:"+childObservable.getKey()+" value:"+integer);
                   }
                });
            }
        });
    }


}