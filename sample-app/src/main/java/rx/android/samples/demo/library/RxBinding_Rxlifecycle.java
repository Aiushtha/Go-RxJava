package rx.android.samples.demo.library;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

import static com.trello.rxlifecycle.FragmentEvent.PAUSE;

/**
 * Created by Lin on 2017/2/6.
 */

public class RxBinding_Rxlifecycle extends BaseFragment {

    public static int count=0;

    @Override
    protected void init() {
        super.init();
        println("开启2个计时器,一个会在绑定生命周期另外一个会在PAUSE事件结束");
    }

    @Override
    public void runCode() {
        super.runCode();
        Observable.interval(2, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object num) {
                        println(count++);
                    }
                });
        Observable.interval(2, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(PAUSE))
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        println(System.currentTimeMillis());
                    }
                });
    }
}
