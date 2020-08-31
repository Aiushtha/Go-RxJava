package rx.android.samples.demo.skill;

import android.app.ProgressDialog;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Compose_Proxy extends BaseFragment {
    @Override
    protected void init() {
        super.init();
    }

    //    Fragment_DoOnTerminate
    public Observable.Transformer createProgressDialog() {
        return new Observable.Transformer<Object, Object>() {
            ProgressDialog pd;

            @Override
            public Observable<Object> call(final Observable<Object> observable) {

                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnRequest(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                println("doOnRequest");
                                pd = ProgressDialog.show(getActivity(), "1111", "222");
                            }
                        })
                        .doOnTerminate(new Action0() {
                            @Override
                            public void call() {
                                println("doOnTerminate");
                                pd.dismiss();
                            }
                        });

            }
        };
    }

    @Override
    public void runCode() {
        super.runCode();
        println(System.currentTimeMillis());
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("网络请求");
                subscriber.onCompleted();

            }
        })
                .compose(createProgressDialog())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                        println(System.currentTimeMillis());
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("onnext:" + e);
                    }

                    @Override
                    public void onNext(String next) {
                        println("onnext:" + next);
                    }
                });

    }
}
