package rx.android.samples.demo.convert;

import java.util.Iterator;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToIterable extends BaseFragment {

    public void runCode() {

//        window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable

//        http://blog.csdn.net/axuanqq/article/details/50756530
        Iterator<Integer> it = Observable.range(0, 5)
                .toBlocking().toIterable().iterator();
        while (it.hasNext()) {
            println(it.next());
        }

    }


}
