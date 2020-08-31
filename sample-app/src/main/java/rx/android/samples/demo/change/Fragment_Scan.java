package rx.android.samples.demo.change;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func2;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Scan extends BaseFragment {

    public void runCode() {
//        sacn操作符是遍历源Observable产生的结果，再按照自定义规则进行运算，依次输出每次计算后的结果给订阅者:
//        call 回掉第一个参数是上次的结算结果，第二个参数是当此的源observable的输入值
        Observable.range(0,8)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer integer) {
                        println("sum:"+sum+" "+"int:"+integer);
                        return sum + integer;
                    }
                }).subscribe(new Observer<Integer>() {
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
                println("------>onNext()" + integer);
            }
        });
    }


}