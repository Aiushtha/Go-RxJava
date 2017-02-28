package rx.android.samples.demo.create;


import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;


/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Repeat extends BaseFragment {

    public void runCode() {

        Observable.just(1, 2, 3).repeat(5)
                .subscribe(new Subscriber<Integer>() {


                    @Override
                    public void onNext(Integer integer) {
                        println("onNext:" + integer);
                    }

                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }


                });
    }


}
