package rx.android.samples.demo.skill;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_DebounceOnEditText extends BaseFragment {

    private EditText editText;
    class SomeOnSubscribe implements Observable.OnSubscribe<Object>{


        @Override
        public void call(final Subscriber<? super Object> subscriber) {
            println("SomeOnSubscribe is call");
            final TextWatcher watcher = new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(editText.getText().toString());
                    }
                }

                @Override public void afterTextChanged(Editable s) {
                }
            };
            editText.addTextChangedListener(watcher);

            subscriber.add(new MainThreadSubscription() {
                @Override protected void onUnsubscribe() {
                    editText.removeTextChangedListener(watcher);
                }
            });
            // Emit initial value.
            subscriber.onNext(editText.getText().toString());
        }
    }

    @Override
    protected void init() {
        super.init();
        editText = new EditText(getActivity());
        container.addView(editText);
        //http://www.jianshu.com/p/1071ba780e4f
         Observable.create(new SomeOnSubscribe())
                .debounce(600, TimeUnit.MILLISECONDS)
                 //ThrottleFirst操作符则会定期发射这个时间段里源Observable发射的第一个数据
                 .throttleFirst(1000,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object s) {
                       println("edit change:"+s);
                    }
                });
        ;



    }

    @Override
    public void runCode() {

    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_submit.setText("运行一下,试着连续点击");
//
//    }

//    Observable observable=
//            Observable.create(new Observable.OnSubscribe<Integer>() {
//                @Override
//                public void call(Subscriber<? super Integer> subscriber) {
//                    for (int i = 0; i < 10; i++) {
//                        if (!subscriber.isUnsubscribed()) {
//                            subscriber.onNext(i);
//                        }
//                        int sleep = 100;
//                        if (i % 3 == 0) {
//                            sleep = 300;
//                        }
//                        try {
//                            Thread.sleep(sleep);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                    subscriber.onCompleted();
//                }
//            }).subscribeOn(Schedulers.newThread());
//
//    public void runCode() {
//        observable
//                .debounce(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
//                .subscribeOn(AndroidSchedulers.mainThread())// 对etKey[EditText]的监听操作 需要在主线程操作
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onCompleted() {
//                        println("onCompleted()");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        println("onError()" + e);
//                    }
//
//                    @Override
//                    public void onNext(Object obj) {
//                        println("onNext()" + obj);
//                    }
//                });
//
//    }


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