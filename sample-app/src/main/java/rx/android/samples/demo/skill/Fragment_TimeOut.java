package rx.android.samples.demo.skill;

import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_TimeOut extends BaseFragment {


    @Override
    protected void init() {
        super.init();
        btn_submit.setText("timeout");
        Button btn = new Button(getActivity());
        btn.setText("amb操作符");
        container.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amb();
            }
        });
    }

    public Observable<Integer> obs1() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(1);
            }
        });
    }

    public Observable<Integer> obs2() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(2);
            }
        });
    }

    public void runCode() {
        time();
    }


    public void time() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .timeout(1200, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("onnext");
                    }
                });

    }

    public void amb() {
        Observable.amb(obs1(), obs2())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        println("onnext" + integer);
                    }
                });
    }

}
