package rx.android.samples.demo.skill;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_RetryWhen extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203


//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_submit.setText("运行一下,试着连续点击");
//
//    }

    public void runCode() {
        //retryWhen操作符实现错误重试机制
        Observable.create(new Observable.OnSubscribe<Integer>() {
            int count;
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                println("start"+Thread.currentThread());
                subscriber.onNext(0);
                subscriber.onNext(1);
                count++;
                if(count<3) {
                    //这里报错了
                    subscriber.onError(new Throwable("do onError"));
                }
                subscriber.onNext(2);
                subscriber.onNext(3);
                //总共重试3次，重试间隔3000毫秒
            }
//        }).retryWhen(new RetryWithDelay(3, 3000 ))}
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 3000))
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
                        println("onNext:" + obj + " " + Thread.currentThread());
                    }
                });

    }

    public class RetryWithDelay implements
            Func1<Observable<? extends Throwable>, Observable<?>> {

        private final int maxRetries;
        private final int retryDelayMillis;
        private int retryCount;

        public RetryWithDelay(int maxRetries, int retryDelayMillis) {
            this.maxRetries = maxRetries;
            this.retryDelayMillis = retryDelayMillis;
        }

        @Override
        public Observable<?> call(Observable<? extends Throwable> attempts) {
            return attempts
                    .flatMap(new Func1<Throwable, Observable<?>>() {
                        @Override
                        public Observable<?> call(Throwable throwable) {
                            if (++retryCount <= maxRetries) {
                                // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                                println("get error, it will try after " + retryDelayMillis
                                        + " millisecond, retry count " + retryCount);
                                return Observable.timer(retryDelayMillis,
                                        TimeUnit.MILLISECONDS);
                            }
                            // Max retries hit. Just pass the error along.
                            return Observable.error(throwable);
                        }
                    });
        }
    }


}
