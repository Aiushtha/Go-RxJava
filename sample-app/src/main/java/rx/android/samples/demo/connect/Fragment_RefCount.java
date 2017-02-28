package rx.android.samples.demo.connect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_RefCount extends BaseFragment {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public void runCode() {
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(4);
        Observable obsRefCount = obs.publish().refCount();

        obs.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                println("普通obs1：onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                println( "普通obs1：onError");
            }
            @Override
            public void onNext(Long along) {
                println("普通obs1：onNext:"+along+"->time:"+ sdf.format(new Date()));
            }
        });
        obs.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        println("普通obs2：onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        println("普通obs2：onError");
                    }
                    @Override
                    public void onNext(Long along) {
                        println("普通obs2：onNext:"+along+"->time:"+ sdf.format(new Date()));
                    }
                });

        obsRefCount.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                println( "obsRefCount1：onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                println("obsRefCount1：onError");
            }
            @Override
            public void onNext(Long along) {
                println("obsRefCount1：onNext:"+along+"->time:"+ sdf.format(new Date()));
            }
        });
        obsRefCount.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        println("obsRefCount2：onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        println( "obsRefCount2：onError");
                    }
                    @Override
                    public void onNext(Long along) {
                        println("obsRefCount2：onNext:"+along+"->time:"+ sdf.format(new Date()));
                    }
                });


    }




}
