package rx.android.samples.demo.skill;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Compose_Demo2 extends BaseFragment {

    //BehaviorSubject订阅subscribe过程
    //在需要使用subject时，调用Subject的subscribe(..)方法，
    // 该方法实际会调用下面这个
    // subscribe(Subscriber<? super T> subscriber)方法
    //，所以其他的subscribe方法都要将输入参数转化为一个Subscriber对象。

    private final BehaviorSubject<String> lifecycleSubject = BehaviorSubject.create();


    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext("onPause");
    }

    @Override
    protected void init() {
        super.init();

    }

    private static <T, R> Observable.Transformer<T, T> bind(Observable<R> lifecycle,
                                                            final Func1<R, R> correspondingEvents) {
        if (lifecycle == null) {
            throw new IllegalArgumentException("Lifecycle must be given");
        }

        // Make sure we're truly comparing a single stream to itself
        final Observable<R> sharedLifecycle = lifecycle.share();

        // Keep emitting from source until the corresponding event occurs in the lifecycle
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> source) {
                return source.takeUntil(
                        Observable.combineLatest(
                                sharedLifecycle.take(1).map(correspondingEvents),
                                sharedLifecycle.skip(1),
                                new Func2<R, R, Boolean>() {
                                    @Override
                                    public Boolean call(R bindUntilEvent, R lifecycleEvent) {
                                        return lifecycleEvent == bindUntilEvent;
                                    }
                                })
                                .onErrorReturn(new Func1<Throwable, Boolean>() {
                                    @Override
                                    public Boolean call(Throwable throwable) {
                                        return null;
                                    }
                                })
                                .takeFirst(new Func1<Boolean, Boolean>() {
                                    @Override
                                    public Boolean call(Boolean aBoolean) {
                                        return null;
                                    }
                                })
                );
            }
        };
    }


    @Override
    public void runCode() {
        super.runCode();
        Observable.just("str")
                .compose(this.<String>bindToLifecycle())
                .subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                println("onCompleted");
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
