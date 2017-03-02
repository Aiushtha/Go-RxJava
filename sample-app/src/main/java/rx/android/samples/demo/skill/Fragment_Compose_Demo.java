package rx.android.samples.demo.skill;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Compose_Demo extends BaseFragment {


    @Override
    protected void init() {
        super.init();

    }
    public Observable.Transformer timer(final long time) {
        return new Observable.Transformer<Object, Object>() {
            @Override
            public Observable<Object> call(final Observable<Object> observable) {
                return observable.flatMap(new Func1<Object, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(Object integer) {
                        //延时1000秒
                        return Observable.timer(time,
                                TimeUnit.MILLISECONDS).flatMap(new Func1<Long, Observable<Object>>() {
                            @Override
                            public Observable<Object> call(Long aLong) {
                                return observable;
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public void runCode() {
        super.runCode();
        println(System.currentTimeMillis());
        Observable.just("str")
                .compose(timer(2000))
                .subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                println("onCompleted");
                println(System.currentTimeMillis());
            }

            @Override
            public void onError(Throwable e) {
                println("onnext:"+e);
            }

            @Override
            public void onNext(String next) {
                println("onnext:"+next);
            }
        });

    }
}
