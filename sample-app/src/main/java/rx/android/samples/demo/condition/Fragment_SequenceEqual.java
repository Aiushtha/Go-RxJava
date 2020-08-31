package rx.android.samples.demo.condition;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_SequenceEqual extends BaseFragment {

    public void runCode() {

//        window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

//        http://blog.csdn.net/axuanqq/article/details/50756530
        Observable
                .sequenceEqual(
                 Observable.just("A","B")
                ,Observable.just("A","B"))
                .subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                println(aBoolean);
            }
        });

    }




}
