package rx.android.samples.demo.skill;

import android.view.View;
import android.widget.Button;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Zip_Runnable extends BaseFragment {

    long time;

    @Override
    protected void init() {
        super.init();
        btn_submit.setText("buffer");
        Button btn = new Button(getActivity());
        btn.setText("zip合并数据");
        container.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zip();
            }
        });
    }

    public Observable<Integer> obs1() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                sleep(2500);
                subscriber.onNext(1);
                println("onNext1:" + (System.currentTimeMillis() - time) / 1000f);
            }
        });
    }

    public Observable<Integer> obs2() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                sleep(1000);
                subscriber.onNext(2);
                println("onNext2:" + (System.currentTimeMillis() - time) / 1000f);
            }
        });
    }

    public Observable<Integer> obs3() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                sleep(1000);
                subscriber.onNext(3);
                println("onNext3:" + (System.currentTimeMillis() - time) / 1000f);
            }
        });
    }


    public Observable<Integer> obs4() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                sleep(2000);
                subscriber.onNext(4);
                println("onNext4:" + (System.currentTimeMillis() - time) / 1000f);
            }
        });
    }

    public Observable<Integer> obs5() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                sleep(2000);
                subscriber.onNext(5);
                println("onNext5:" + (System.currentTimeMillis() - time) / 1000f);
            }
        });
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runCode() {
        time = System.currentTimeMillis();
        println(time);
        Observable<Integer>[] arr = new Observable[]{obs1(), obs2(), obs3(), obs4(), obs5()};
        Observable.from(arr)
                .flatMap(new Func1<Observable<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Observable<Integer> integerObservable) {
                        return integerObservable.subscribeOn(Schedulers.newThread());
                    }
                })
                .buffer(arr.length)
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> arr) {
                        println("onnext:" + arr + " " + ((System.currentTimeMillis() - time) / 1000f));
                    }
                });
    }

    public void zip() {
        time = System.currentTimeMillis();
        println(time);
        Observable<Integer>[] arr = new Observable[]{
                obs1().subscribeOn(Schedulers.newThread()),
                obs2().subscribeOn(Schedulers.newThread()),
                obs3().subscribeOn(Schedulers.newThread()),
                obs4().subscribeOn(Schedulers.newThread()),
                obs5().subscribeOn(Schedulers.newThread())};

        Observable.zip(arr, new FuncN<Integer[]>() {
            @Override
            public Integer[] call(Object... args) {
                Integer[] arr = new Integer[args.length];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.valueOf(args[i].toString());
                }
                return arr;
            }
        })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer[]>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted:" + System.currentTimeMillis());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer[] os) {
                        println("onnext:" + " " + ((System.currentTimeMillis() - time) / 1000f));
                        for (Integer i : os) {
                            println(i);
                        }
                    }
                });
        ;


    }
}
