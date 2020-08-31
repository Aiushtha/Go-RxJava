package rx.android.samples.demo.library;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2017/2/6.
 */

public class RxBinding_StableClick extends BaseFragment {

    @Override
    protected void init() {
        super.init();
        RxView.clicks(btn_submit)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        println("click");
                    }
                });
    }

}
