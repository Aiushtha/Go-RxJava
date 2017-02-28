package rx.android.samples.demo.convert;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 2016/10/13.
 */

public class Fragment_ToMultiMap extends BaseFragment {

    public void runCode() {
        Observable.concat(
                Observable.just(1, 2, 3, 4),
                Observable.just(2, 3, 4, 5)
        )
                .toMultimap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "ID:" + integer;
                    }
                }).subscribe(new Subscriber<Map<String, Collection<Integer>>>() {
            @Override
            public void onCompleted() {
                println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Map<String, Collection<Integer>> map) {
                Iterator<Map.Entry<String, Collection<Integer>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Collection<Integer>> keyValue = it.next();
                    println(keyValue.getKey());
                    println(keyValue.getValue());
                }

            }
        });
    }


}
