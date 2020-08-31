package rx.android.samples.demo.create;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Empty_Nerver_Throw extends BaseFragment {

    public void runCode() {


        /***
         * Empty，Never和Throw运算符生成具有非常特定和有限行为的Observable。
         * 这些用于测试目的，有时也用于与其他Observable结合或作为参数，希望其他Observable作为参数的运算符。
         * Observable.empty() 需要一个 Oservable 但是什么都不发射
         * Observable.never() 传一个不发射数据并永远不会结束的 Observable
         * Observable.throw() 创建一个不发射数据并且以错误结束的 Observable
         */

        Observable.never()
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onCompleted() {
                        println("never Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("never error");
                    }

                    @Override
                    public void onNext(Object o) {
                        println("never next");
                    }
                });
        ;

        Observable.empty()
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onCompleted() {
                        println("empty Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("empty error");
                    }

                    @Override
                    public void onNext(Object o) {
                        println("empty next");
                    }
                });
        ;
        Observable.error(new RuntimeException("error"))
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onCompleted() {
                        println("error Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("error error");
                    }

                    @Override
                    public void onNext(Object o) {
                        println("error next");
                    }
                });

    }

}
