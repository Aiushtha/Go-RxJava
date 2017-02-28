package rx.android.samples.demo.assist;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Delay extends BaseFragment {


    public void runCode() {
        println("------->start");
        final long time=System.currentTimeMillis();
        //Delay操作符就是让发射数据的时机延后一段时间，这样所有的数据都会依次延后一段时间发射

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
                .delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

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
