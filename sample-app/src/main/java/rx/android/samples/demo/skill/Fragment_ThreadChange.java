package rx.android.samples.demo.skill;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_ThreadChange extends BaseFragment {

    //http://blog.csdn.net/johnny901114/article/details/51555203


//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_submit.setText("运行一下,试着连续点击");
//
//    }

    public class User {
        public int id;

        public User(int id) {
            this.id = id;
        }
    }


    public Observable<String> typeString() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                println("type String:" + Thread.currentThread());
                subscriber.onNext("1");
            }
        });
    }

    public Observable<Integer> typeInteger(final int i) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                println("type String:" + Thread.currentThread());
                subscriber.onNext(Integer.valueOf(i));
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<User> typeUser(final int id) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                println("type User:" + Thread.currentThread());
                subscriber.onNext(new User(id));
            }
        });
    }

    public void runCode() {
        typeString()
        .subscribeOn(Schedulers.newThread())
        .flatMap(new Func1<String, Observable<Integer>>() {
         @Override
         public Observable<Integer> call(String s) {
          return typeInteger(Integer.valueOf(s));
         }
        }).flatMap(new Func1<Integer, Observable<User>>() {
          @Override
          public Observable<User> call(Integer integer) {
           return typeUser(integer);
          }
        }).observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<User>() {
          @Override
          public void onCompleted() {}
          @Override
          public void onError(Throwable e) {
          }
          @Override
          public void onNext(User user) {
           println("onNext:" + user + Thread.currentThread());
          }
        });
    }
}
