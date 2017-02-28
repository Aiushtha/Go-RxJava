package rx.android.samples.demo.assist;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_DoOnError extends BaseFragment {

    public void runCode() {
//        doOnError会在OnError发生的时候触发回调，并将Throwable对象作为参数传进回调函数里；
        try {
            Observable.error(new Throwable("呵呵哒")).doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    println(throwable.getMessage().toString());
                }
            }).subscribe();
        }catch (Exception e){
            println("catch the exception");
        }
    }


}
