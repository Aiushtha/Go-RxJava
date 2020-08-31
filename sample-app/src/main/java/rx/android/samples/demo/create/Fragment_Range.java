package rx.android.samples.demo.create;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Range extends BaseFragment {

    public void runCode() {
        //http://blog.csdn.net/new_abc/article/details/48025513
        Observable.range(0,10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(integer);
                    }
                });
        println("");
        Observable.range(23,3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(integer);
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
