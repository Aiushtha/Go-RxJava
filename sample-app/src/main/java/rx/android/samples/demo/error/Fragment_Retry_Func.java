package rx.android.samples.demo.error;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func2;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Retry_Func extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203


//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_submit.setText("运行一下,试着连续点击");
//
//    }
    public int k=0;
    public void runCode() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(0);
                subscriber.onNext(1);
                k++;
                if(k<=5) {
                    //这里报错了
                    subscriber.onError(new Throwable("do onError"));
                }
                subscriber.onNext(2);
                subscriber.onNext(3);
            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                println(integer);
                if(k==4)
                {
                    return false;
                }
                return true;
            }
        }).subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        println("-------->onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("-------->onError()" + e);
                    }

                    @Override
                    public void onNext(Object obj) {
                        println("-------->onNext()" + obj);
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