package rx.android.samples.demo.group;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Switch extends BaseFragment {

    public void runCode() {
        Observable.just("A", "B", "C", "D", "E").switchMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                Observable<String> ob = Observable.just(s);
                return ob;
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("------>onError()" + e);
            }

            @Override
            public void onNext(String s) {
                println("------>onNext:" + s);
            }
        });

    }


}
