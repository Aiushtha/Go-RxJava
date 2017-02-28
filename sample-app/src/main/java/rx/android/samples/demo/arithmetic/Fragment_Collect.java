package rx.android.samples.demo.arithmetic;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_Collect extends BaseFragment {

    public void runCode() {

        Observable<Integer> observable = Observable.just(1,2,3,4,5);

        Func0<ArrayList<Integer>> stateFactory = new Func0<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() {
                return new ArrayList<Integer>();
            }
        };

        Action2<ArrayList<Integer>, Integer> collector = new Action2<ArrayList<Integer>, Integer>() {

            @Override
            public void call(ArrayList<Integer> integers, Integer i) {
                println("integer:"+i);
                integers.add(i);
            }
        } ;

        Subscriber<ArrayList<Integer>> subscriber = new Subscriber<ArrayList<Integer>>() {
            @Override
            public void onNext(ArrayList<Integer> v) {
                println( "onNext:." + v.toString());
            }

            @Override
            public void onCompleted() {
                println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                println("onError.....................");
            }
        };

        observable
                .collect(stateFactory,collector)
                .subscribe(subscriber);


    }


}
