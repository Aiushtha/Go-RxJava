package rx.android.samples.demo.connect;

import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.observables.ConnectableObservable;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Connect extends BaseFragment {

    @Override
    protected void init() {
        super.init();
        Button btn = new Button(getActivity());
        btn.setText("反例");
        container.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unConnect();
            }


        });
    }

    public void runCode() {

//        window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

//        http://blog.csdn.net/axuanqq/article/details/50756530

        //sample 操作符定期扫描源Observable产生的结果
        ConnectableObservable observable = Observable.range(1, 1000000).sample(10, TimeUnit.MILLISECONDS).publish();

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted1.");
            }

            @Override
            public void onError(Throwable e) {
                println("onError1: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                println("onNext1: " + integer);
            }
        });

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted2.");
            }

            @Override
            public void onError(Throwable e) {
                println("onError2: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                println("onNext2: " + integer);
            }
        });

        observable.connect();

    }


    private void unConnect() {

        Observable observable = Observable.range(1, 1000000)
                .sample(10, TimeUnit.MILLISECONDS);

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted1.");
            }

            @Override
            public void onError(Throwable e) {
                println("onError1: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                println("onNext1: " + integer);
            }
        });

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                println("onCompleted2.");
            }

            @Override
            public void onError(Throwable e) {
                println("onError2: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                println("onNext2: " + integer);
            }
        });
    }




}
