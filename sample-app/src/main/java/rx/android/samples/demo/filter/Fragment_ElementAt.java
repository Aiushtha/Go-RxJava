package rx.android.samples.demo.filter;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_ElementAt extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203



    public void runCode() {
        Observable.range(1, 10).elementAt(5).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                println("------>onError()" + e);
            }

            @Override
            public void onNext(Integer integer) {
                println("------->onNext()" + integer);
            }
        });

    }


}
