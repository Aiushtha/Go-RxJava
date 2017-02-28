package rx.android.samples.demo.connect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Refplay extends BaseFragment {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public void runCode() {
        println("start time:" + sdf.format(new Date()));
        //没有缓存的情况
        ConnectableObservable<Long> obs = Observable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .publish();
        obs.connect();  //开始发射数据
        obs.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                               @Override
                               public void call(Long aLong) {
                                   println("onNext:" + aLong + "->time:" + sdf.format(new Date()));
                               }
                           });

        //缓存一个数据
        ConnectableObservable<Long> obs1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .replay(1);   //缓存1个数据
        obs1.connect();  //开始发射数据
        obs1.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        println("1.onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }
                });

        //缓存3s内发射的数据
        ConnectableObservable<Long> obs2 = Observable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .replay(3, TimeUnit.SECONDS);   //缓存3s
        obs2.connect();  //开始发射数据
        obs2.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        println( "2.onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }
                });
    }




}
