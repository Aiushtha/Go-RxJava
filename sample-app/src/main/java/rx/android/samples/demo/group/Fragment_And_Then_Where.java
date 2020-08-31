package rx.android.samples.demo.group;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.joins.Plan0;
import rx.observables.JoinObservable;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_And_Then_Where extends BaseFragment {

    //http://blog.chinaunix.net/uid-20771867-id-5194384


//    And,Then和When操作符是在RxJava的joins包下，使用Pattern和Plan作为中介，将发射的数据集合并到一起。需要导包joins包，AndroidStudio中在gradle引用：compile 'io.reactivex:rxjava-joins:0.22.0'，如果不引用此包，类Pattern、Plan和JoinObservable不存在.

    public void runCode() {

        Observable obs1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable obs2 = Observable.range(0,3);
        Plan0 plan0 = JoinObservable.from(obs1).and(obs2).then(new Func2() {
            @Override
            public Object call(Object o1, Object o2) {
                return o2;
            }
        });
        JoinObservable.when(plan0).toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
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
                        println("onNext:"+integer);
                    }

                });


    }


}
