package rx.android.samples.demo.convert;

import java.util.List;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToFuture extends BaseFragment {

    public void runCode() {

//        window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

//        http://blog.csdn.net/axuanqq/article/details/50756530
        try {
            List<Integer> list = Observable.just(1, 2, 3)
                    .toList()
                    .toBlocking()
                    .toFuture().get();
            println(list);
        } catch (Exception e) {
            e.printStackTrace();
            println(e);
        }


    }




}
