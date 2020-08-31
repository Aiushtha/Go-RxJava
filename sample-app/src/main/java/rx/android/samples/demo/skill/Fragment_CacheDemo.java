package rx.android.samples.demo.skill;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_CacheDemo extends BaseFragment {


    @Override
    protected void init() {
        super.init();
        Button btn1=new Button(getActivity());
        container.addView(btn1);
        btn1.setText("清除内存变量");
        Button btn2=new Button(getActivity());
        container.addView(btn2);
        btn2.setText("清除本地缓存");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId=null;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                sp.edit().putString("userId",null).commit();
            }
        });
    }

    public String userId;

    public void saveLocalData(String data)
    {
        SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        sp.edit().putString("userId",userId).commit();
    }

    //从内存中获取数据
    public Observable<String> memory(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
               //如果某变量不为空就用变量的值
               if(userId!=null)
               {
                   println("memory:"+Thread.currentThread());
                   subscriber.onNext(userId+" "+"source:memory");
               }else
               {
                   subscriber.onCompleted();
               }
            }

        }).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io());
    };

    //从本地缓存获取数据
    public Observable<String> localData(){
       return Observable.create(new Observable.OnSubscribe<String>() {
           @Override
           public void call(Subscriber<? super String> subscriber) {
               println("local:"+Thread.currentThread());
               String data = getLocalData();
               if(data!=null)
               {
                   userId=data;
                   subscriber.onNext(userId+" "+"source:local");
                   subscriber.onCompleted();
               }else {
                   subscriber.onCompleted();
               }

           }

       }).observeOn(AndroidSchedulers.mainThread())
         .subscribeOn(Schedulers.io());
    };


    public String getLocalData()
    {
        SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sp.getString("userId", null);
    }

    //从网络获取数据
    public Observable<String> netWorkData(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userId="从网络获取netAAA";
                saveLocalData(userId);
                println("net:"+Thread.currentThread());
                subscriber.onNext(userId+" "+"source:net");
                subscriber.onCompleted();


            }

        }).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io());
    };

    //从三个途径获取userId
    public Observable<String> getUserID(){
        return Observable.concat(
                memory(),
                localData(),
                netWorkData());
//        ).first();
    }

    public void runCode() {
        getUserID()
                .subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String i) {

                        println("end:"+Thread.currentThread());
                        println("onNext:" +i);
                    }
                });

    }




}
