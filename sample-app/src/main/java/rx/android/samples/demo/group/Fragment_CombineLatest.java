package rx.android.samples.demo.group;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func2;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_CombineLatest extends BaseFragment {


    public void runCode() {
        Observable<String> o1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("o1");
                subscriber.onNext("o2");
                subscriber.onNext("o3");
            }
        });
        Observable<String> o2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("o4");
                subscriber.onNext("o5");
                subscriber.onNext("o6");
            }
        });
        Observable.combineLatest(o2, o1, new Func2<String, String, String>() {
            @Override public String call(String s, String s2) {
                println("combine --- >"+ "s = " + s + " | s2 = " + s2);
                return s + s2;
            }
        }).subscribe(new Observer<String>() {
            @Override public void onCompleted() {
                println("onCompleted --- >"+"onCompleted");
            }

            @Override public void onError(Throwable e) {
                println("onError --- >"+"onError");

            }

            @Override public void onNext(String o) {
                println("onNext --- >"+ o);
            }
        });
    }



}
