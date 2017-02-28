package rx.android.samples.demo.convert;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToList extends BaseFragment {


    public void runCode() {
        Observable.range(0, 5)
               .toList()
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> i) {
                       println("onnext"+i);
                    }
                });
    }




}
