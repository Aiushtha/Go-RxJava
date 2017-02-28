package rx.android.samples.demo.assist;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/12/21.
 */

public class Fragment_DelaySubscription  extends BaseFragment {

    public void runCode() {
        final long time=System.currentTimeMillis();
//        不同之处在于Delay是延时数据的发射，而DelaySubscription是延时注册Subscriber。
//        dealy是延迟发射，delaySubscription则是延迟收到
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i <= 10; i++) {
                    subscriber.onNext((int)((System.currentTimeMillis()-time)/1000));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .delaySubscription(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("------>onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("------>onError()" + e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("------->onNext()"+integer+"秒");
                    }
                });


    }
}