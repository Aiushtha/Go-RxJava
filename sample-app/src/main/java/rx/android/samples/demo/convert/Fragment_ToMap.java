package rx.android.samples.demo.convert;

import java.util.Iterator;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToMap extends BaseFragment {

    public void runCode() {
        Observable.just(1, 2, 3, 4)
                .toMap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "ID:" + integer;
                    }
                })
                .subscribe(new Observer<Map<String, Integer>>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Map<String, Integer> map) {
                        println("onNext");
                        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, Integer> keyValue = it.next();
                            println(keyValue.getKey());
                            println(keyValue.getValue());
                        }
                    }
                });

    }


}
