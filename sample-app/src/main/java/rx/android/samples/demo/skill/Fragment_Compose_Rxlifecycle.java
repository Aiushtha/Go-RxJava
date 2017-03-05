package rx.android.samples.demo.skill;

import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Compose_Rxlifecycle extends BaseFragment {

    //BehaviorSubject订阅subscribe过程
    //在需要使用subject时，调用Subject的subscribe(..)方法，
    // 该方法实际会调用下面这个
    // subscribe(Subscriber<? super T> subscriber)方法
    //，所以其他的subscribe方法都要将输入参数转化为一个Subscriber对象。

    private final BehaviorSubject<String> lifecycleSubject = BehaviorSubject.create();
    private Button btn_pause;



    @Override
    protected void init() {
        super.init();
        btn_pause=new Button(getActivity());
        btn_pause.setText("pause");
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifecycleSubject.onNext("onPause");
            }
        });
        container.addView(btn_pause);

    }

    private <T, R> Observable.Transformer<T, T> bind(Observable<R> lifecycle,
                                                     final Func1<R, R> correspondingEvents) {
        if (lifecycle == null) {
            throw new IllegalArgumentException("Lifecycle must be given");
        }

        //确保我们真的比较它的单一流
        // Make sure we're truly comparing a single stream to itself
        final Observable<R> sharedLifecycle = lifecycle.share();

        // Keep emitting from source until the corresponding event occurs in the lifecycle
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> source) {

                //过滤
                return source.takeUntil(
                        //连接2个Observable,并且通过Func2只返回一个Observable
                        Observable.combineLatest(
                                //获得correspondingEvents变量的第一个元素
                                sharedLifecycle.take(1).map(correspondingEvents),
                                //忽略第一次暂停 从第二次开始暂停
                                sharedLifecycle.skip(1),
                                new Func2<R, R, Boolean>() {
                                    @Override
                                    public Boolean call(R bindUntilEvent, R lifecycleEvent) {
                                        println("bindUntilEvent:"+bindUntilEvent+" "+"lifecycleEvent:"+lifecycleEvent);
                                        return bindUntilEvent.equals(lifecycleEvent);
                                    }
                                })
                                .onErrorReturn(new Func1<Throwable, Boolean>() {
                                    @Override
                                    public Boolean call(Throwable throwable) {
                                        println("throwable:" + throwable);
                                        return null;
                                    }
                                })
                                .takeFirst(new Func1<Boolean, Boolean>() {
                                    @Override
                                    public Boolean call(Boolean aBoolean) {
                                        println("aBoolean:" + aBoolean);
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
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("start");

                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext("" + i);
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 会影响上面的doOnSubscribe()  .subscribe(new Observer<Integer>() {
                .observeOn(AndroidSchedulers.mainThread()) // 不会影响上面的doOnSubscribe()
                .compose(bind(lifecycleSubject, new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        println(s);
                        return s;
                    }
                }))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        println("onnext:" + o);
                    }
                });
//                .subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                println("onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                println("onnext:"+e);
//            }
//
//            @Override
//            public void onNext(String next) {
//                println("onnext:"+next);
//            }
//        });
//
    }
}
