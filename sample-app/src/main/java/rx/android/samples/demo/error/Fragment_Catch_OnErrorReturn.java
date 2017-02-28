package rx.android.samples.demo.error;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Catch_OnErrorReturn extends BaseFragment {

    //http://blog.csdn.net/io_field/article/details/52439967
    public void runCode() {

//        onErrorReturn:让Observable遇到错误时发射一个特殊的项并且正常终止。
//        onErrorResumeNext:让Observable在遇到错误时开始发射第二个Observable的数据序列。
//        onExceptionResumeNext:让Observable在遇到错误时继续发射后面的数据项。


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(0);
                subscriber.onNext(1);
                //这里报错了
                subscriber.onError(new Throwable("do onError"));
                subscriber.onNext(2);
                subscriber.onNext(3);
            }
        }).onErrorReturn(new Func1<Throwable, Integer>() {
                    @Override
                    public Integer call(Throwable throwable) {
                        return -1;
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Object obj) {
                        println("-------->onNext()" + obj);
                    }
                });

    }


}
