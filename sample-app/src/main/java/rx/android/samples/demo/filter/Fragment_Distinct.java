package rx.android.samples.demo.filter;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Distinct extends BaseFragment {



    //http://blog.csdn.net/axuanqq/article/details/50764256

    public void runCode() {
        Observable.just(1, 2, 3, 1, 2, 3, 12, 3)
            .distinct()
            .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
              println(integer);
            }
        });


    }


}
//.create(new Observable.OnSubscribe<Long>() {
//@Override
//public void call(Subscriber subscriber) {
//        println(String.valueOf("当前时间:"+System.currentTimeMillis()));
//        println(String.valueOf("等待二秒后执行"));
//        subscriber.onNext(System.currentTimeMillis());
//        subscriber.onCompleted();
//        }
//
//        })