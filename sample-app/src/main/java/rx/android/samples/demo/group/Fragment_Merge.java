package rx.android.samples.demo.group;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Merge extends BaseFragment {

   //http://blog.chinaunix.net/uid-20771867-id-5194384



    public void runCode() {
        Observable observable1=Observable.just(1);
        Observable observable2=Observable.just(2);
        Observable observable3=Observable.just(3);
        Observable.merge(observable1,observable2,observable3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("-------->onNext()" + integer);
                    }
                });


    }


}
