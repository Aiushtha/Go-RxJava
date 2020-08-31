package rx.android.samples.demo.skill;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2017/2/16.
 */

public class Fragment_Okhttp extends BaseFragment {

    public class ClientHeler {
        OkHttpClient okHttpClient = new OkHttpClient();

        /**
         * 为观察者（订阅者）设置返回数据
         */
        protected <T> void setData(Subscriber<? super T> subscriber, String json, Class<T> clazz) {
            if (TextUtils.isEmpty(json)) {
                subscriber.onError(new Throwable("not data"));
                return;
            }

            T data = new Gson().fromJson(json, clazz);

            subscriber.onNext(data);
            subscriber.onCompleted();
        }

        public String execute2String(Request request) {

            String result = null;
            try {
                Response response = okHttpClient.newCall(request).execute();
                if (response != null && response.isSuccessful()) {
                    result = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * 创建一个工作在IO线程的被观察者(被订阅者)对象
         *
         * @param url
         */
        public <T> Observable<T> createObservable(final String url, final Class<T> clazz) {
            return Observable.create(new Observable.OnSubscribe<T>() {
                @Override
                public void call(Subscriber<? super T> subscriber) {
                    Request.Builder request = new Request.Builder();

                    Request.Builder builder = request.url(url);
                    String json = execute2String(builder.build());
                    setData(subscriber, json, clazz);
                }
            }).subscribeOn(Schedulers.io());
        }
    }


    @Override
    public void runCode() {
        class Bean {
            Object[] results;
            String status;
        }
        String url = "http://api.map.baidu.com/geocoder?address=%E4%B8%8A%E5%9C%B0%E5%8D%81%E8%A1%9710%E5%8F%B7&output=json&key=for11fKwBNsL6FG40PKRYUC7";
        Observable<Bean> observable = new ClientHeler().createObservable(url, Bean.class);
        observable.compose(this.<Bean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bean>() {
                    @Override
                    public void call(Bean data) {
                        println("Get Result:\r\n" + data.status);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        println("Get Error:\r\n" + throwable.getMessage());
                    }
                });

        super.runCode();
    }
}
