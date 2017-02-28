package rx.android.samples.demo.assist;

import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Using extends BaseFragment {

    private Subscriber subscriber;
    @Override
    protected void init() {
        super.init();
        Button btn=new Button(getActivity());
        btn.setText("取消订阅");
        container.addView(btn);
        container.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subscriber!=null) {
                    println("unSubscrib");
                    subscriber.unsubscribe();
                }
            }
        });
    }

    public void runCode() {
//        http://blog.chinaunix.net/uid-20771867-id-5206187.html
        println("5秒后取消订阅,取消订阅时都会释放资源");
        Observable<Long> observable = usingObserver();
        subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                println("onError");
            }

            @Override
            public void onNext(Object o) {
                println("onNext:"+o);
            }
        };
        println("using");
        observable.subscribe(subscriber);
    }

    private Observable<Long> usingObserver() {
        return Observable.using(new Func0<Animal>() {
            @Override
            public Animal call() {
                return new Animal();
            }
        }, new Func1<Animal,Observable<? extends Long>>() {
            @Override
            public Observable<? extends Long> call(Animal o) {
                return Observable.timer(5000,TimeUnit.MILLISECONDS);
            }
        }, new Action1<Animal>() {
            @Override
            public void call(Animal o) {
              o.relase();
            }
        });
    }

    private class Animal {
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                println("onCompleted-2");
            }

            @Override
            public void onError(Throwable e) {
                println("onError-2");
            }

            @Override
            public void onNext(Object o) {
                println("onNext-2:"+o);
            }
        };

        public Animal() {
            println("create animal");

            Observable.interval(1000, TimeUnit.MILLISECONDS)
                    .subscribe(subscriber);
        }

        public void relase() {
            println("animal released");
            subscriber.unsubscribe();
        }
    }


}
