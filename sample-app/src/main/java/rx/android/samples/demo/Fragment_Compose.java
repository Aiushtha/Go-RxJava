package rx.android.samples.demo;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Compose extends BaseFragment {

    public void runCode() {
        //http://blog.csdn.net/new_abc/article/details/48025513

        Integer[] arr = {1, 2, 3};
        Observable.from(arr)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        Observable observable = Observable.create(new Observable.OnSubscribe() {
                            @Override
                            public void call(Object o) {

                            }
                        });
                        return observable;
                    }

                })
                .subscribe(new Observer(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        println(""+Thread.currentThread());

                    }
                });


    }


//    Observable observable = Observable.just(String.valueOf(integer));
//    switch ((int)integer) {
//        case 1:
//            println("A");
//            observable.subscribeOn(AndroidSchedulers.mainThread());
//            observable.observeOn(AndroidSchedulers.mainThread());
//            break;
//        case 2:
//            println("B");
//            observable.subscribeOn(Schedulers.io());
//            observable.observeOn(Schedulers.io());
//            break;
//        case 3:
//            println("C");
//            observable.subscribeOn(Schedulers.newThread());
//            observable.observeOn(Schedulers.newThread());
//            break;
//    }


}
