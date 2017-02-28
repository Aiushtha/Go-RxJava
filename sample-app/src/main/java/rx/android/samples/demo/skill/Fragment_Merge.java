package rx.android.samples.demo.skill;

import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action1;

/**
 * Created by Lin on 2017/2/20.
 */

public class Fragment_Merge extends BaseFragment {


    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void init() {
        super.init();

        btn1 = new Button(getActivity());
        btn1.setText("text1");
        container.addView(btn1);

        btn2 = new Button(getActivity());
        btn2.setText("text2");
        container.addView(btn2);

        btn3 = new Button(getActivity());
        btn3.setText("text3");
        container.addView(btn3);

        Observable obs1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext("1");
                    }
                });
            }
        });
        Observable obs2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext("2");
                    }
                });
            }
        });
        Observable obs3 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext("3");
                    }
                });
            }
        });
        Observable.merge(obs1,obs2,obs3)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object s) {
                        println("click:" + s);
                    }
                });
    }


}
