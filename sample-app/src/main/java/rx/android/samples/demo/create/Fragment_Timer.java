package rx.android.samples.demo.create;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Timer extends BaseFragment {

    public void runCode() {
        println(String.valueOf("等待2秒后打印"));
        Observable
                .timer(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        println(Thread.currentThread() + " " + String.valueOf("onCompleted"));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        println(aLong);

                    }
                });


    }


}
//.create(new Observable.OnSubscribe<Long>() {
//@Override
//public void call(Subscriber subscriber) {
//        println(String.valueOf("当前时间:"+System.currentTimeMillis()));
//        println(String.valueOf("等待二秒后执行"));
//        subscriber.onNext(System.currentTimeMillis());
//        subscriber.onCompleted();
//        }
//
//        })