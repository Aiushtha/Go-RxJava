package rx.android.samples.demo.group;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Lin on 16/10/9.
 */

public class Fragment_Join extends BaseFragment {

//    作者：张磊(BaronZhang)
//    链接：https://zhuanlan.zhihu.com/p/22039934
//    来源：知乎
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public void runCode() {
        class User {
            public String name;
            public String getName() {return name;}

            public User setName(String name) {this.name = name;return this;}
        }

        final List<User> houses = new ArrayList<User>();
        houses.add(new User().setName("1"));
        houses.add(new User().setName("2"));
        houses.add(new User().setName("3"));
        //模拟的房源数据，用于测试 //用来每秒从houses总取出一套房源并发射出去
        Observable<User> houseSequence =
                Observable.interval(1, TimeUnit.SECONDS).map(new Func1<Long, User>() {
                    @Override
                    public User call(Long position) {
                       return houses.get(position.intValue());
                    }
                }).take(houses.size());

        //这里的take是为了防止houses.get(position.intValue())数组越界
        // 用来实现每秒发送一个新的Long型数据
        Observable<Long> tictoc = Observable.interval(2, TimeUnit.SECONDS);
        houseSequence.join(tictoc, new Func1<User, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(User house) {
//                        println("action1---");
                        house.setName(house.getName()+" count:");
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                },
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
//                        println("action2---");
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                },
                new Func2<User, Long, String>() {
                    @Override
                    public String call(User house, Long aLong) {
//                        println("action3---");
                        house.setName(house.getName()+"★");
                        return "第"+aLong+"次" + "-->" + house.getName();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        println("Error:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        println(s);
                    }
                });


//        obs_stu.join(obs_time,func1,func2).observeOn(AndroidSchedulers.mainThread());


    }


}
