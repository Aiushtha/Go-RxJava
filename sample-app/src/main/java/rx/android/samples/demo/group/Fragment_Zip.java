package rx.android.samples.demo.group;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Zip extends BaseFragment {

   //http://blog.chinaunix.net/uid-20771867-id-5194384



    public void runCode() {
        Observable<Integer> obs1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
              println("1 "+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread());

        Observable<Integer> obs2 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                println("2 "+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<Integer> obs3 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                println("3 "+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable.zip(obs1, obs2, obs3,null).subscribe();



//        作者：张磊(BaronZhang)
//        链接：https://zhuanlan.zhihu.com/p/22039934
//        来源：知乎
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
//        Observable<String> letterSequence =
//                Observable.interval(120, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, String>()
//                { @Override public String call(Long position)
//                { return letters[position.intValue()]; } }).take(letters.length);
//        Observable<Long> numberSequence =
//                Observable.interval(200, TimeUnit.MILLISECONDS).take(5);
//        Observable.zip(letterSequence, numberSequence,
//                new Func2<String, Long, String>()
//                { @Override public String call(String letter, Long number)
//                { return letter + number; } }).subscribe(new Observer<String>()
//        { @Override public void onCompleted() { System.exit(0); }
//            @Override public void onError(Throwable e) { System.out.println("Error:" + e.getMessage()); }
//            @Override public void onNext(String result) { System.out.print(result + " "); } });

    }


}
