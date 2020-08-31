package rx.android.samples.demo.assist;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ObserveOn extends BaseFragment {


    private Observable<String> observerOnObserver() {
        return createObserver("A")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    private Observable<String> subscribeOnObserver() {
        return createObserver("B")
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.immediate());
    }

    private Observable<String> createObserver(final String name) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                println("name:"+name);
                println("subscrib:" + Thread.currentThread().getName());
                subscriber.onNext(name);
                subscriber.onCompleted();
            }
        });
    }

    private Observer<String> createSubscribe(){
        return new Observer<String>() {
            String name;
            @Override
            public void onCompleted() {
                println("end:"+name+" "+Thread.currentThread());
                println("");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String str) {
                name=str;
                println(str+"-onNext:"+Thread.currentThread());
            }
        };
    }

    public void runCode() {
       observerOnObserver().subscribe(createSubscribe());
       subscribeOnObserver().subscribe(createSubscribe());
    }
}
