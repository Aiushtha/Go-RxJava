package rx.android.samples.demo.change;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Window extends BaseFragment {

    public void runCode() {

//        window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

//        http://blog.csdn.net/axuanqq/article/details/50756530
        Observable
                .interval(1, TimeUnit.SECONDS).take(10)
                .window(3, TimeUnit.SECONDS).subscribe(new Observer<Observable<Long>>() {
            @Override
            public void onCompleted() {
                println("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("onError()" + e);
            }

            @Override
            public void onNext(Observable<Long> integerObservable) {
                println("onNext()");
                integerObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long integer) {
                        println("call():" + integer);
                    }
                });
            }
        });

    }


}
