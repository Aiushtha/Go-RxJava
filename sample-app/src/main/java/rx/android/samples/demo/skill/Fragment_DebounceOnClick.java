package rx.android.samples.demo.skill;

import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_DebounceOnClick extends BaseFragment {
    //http://blog.csdn.net/jdsjlzx/article/details/51730162
    //http://blog.csdn.net/johnny901114/article/details/51555203
    //https://github.com/f2prateek/rx-preferences
    //https://github.com/cn-ljb/rxjava_for_android



    class SomeOnSubscribe implements Observable.OnSubscribe<Object>{

        @Override
        public void call(final Subscriber<? super Object> subscriber) {
            println("SomeOnSubscribe is call");
            btn_submit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    println("SomeOnSubscribe is click");
                    subscriber.onNext("click event");
                }
            });
        }
    }

    @Override
    protected void init() {
        super.init();
        //http://www.jianshu.com/p/1071ba780e4f
         Observable.create(new SomeOnSubscribe())
                .debounce(600, TimeUnit.MILLISECONDS)
                 //ThrottleFirst操作符则会定期发射这个时间段里源Observable发射的第一个数据
                 .throttleFirst(1000,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object s) {
                       println("click:"+s);
                    }
                });
    }

    @Override
    public void runCode() {

    }


}
