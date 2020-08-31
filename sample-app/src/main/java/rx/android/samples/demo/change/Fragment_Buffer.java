package rx.android.samples.demo.change;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Buffer extends BaseFragment {

    public void runCode() {
//        buffer操作符的功能:
//        1：能一次性集齐多个结果到列表中，订阅后自动清空相应结果,直到完全清除
//        2： 也可以周期性的集齐多个结果到列表中，订阅后自动清空相应结果,直到完全清除
        Observable
                .range(0,8)
                .buffer(5, 5)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted:");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Integer> strings) {
                        println("onNext:" + strings);
                    }
                });

    }


}
