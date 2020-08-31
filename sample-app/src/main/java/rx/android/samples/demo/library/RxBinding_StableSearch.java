package rx.android.samples.demo.library;

import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.R;
import rx.android.samples.demo.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxBinding_StableSearch extends BaseFragment {
    private EditText editText;
    private ListView listView;
    private ArrayAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        initLayout();

        initEditText();
        initPage(new ArrayList<String>());
    }

    private void initLayout() {
        editText = new EditText(getActivity());
        listView = new ListView(getActivity());
        scrollView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        contentView.addView(editText);
        contentView.addView(listView);
    }

    private void initEditText() {
        RxTextView.textChangeEvents(editText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        boolean filter = !TextUtils.isEmpty(textViewTextChangeEvent.text());
                        if (!filter && mAdapter != null) {
                            //操作UI，这里必须在主线程完成
                            boolean b = Thread.currentThread() == Looper.getMainLooper().getThread();
                            mAdapter.clear();
                            mAdapter.notifyDataSetChanged();
                        }
                        return filter;
                    }
                })
                .switchMap(new Func1<TextViewTextChangeEvent, Observable<List<String>>>() {
                    @Override
                    public Observable<List<String>> call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return getKeyWordFormNet(textViewTextChangeEvent.text().toString().trim())
                                .subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  //触发后回到Android主线程调度器
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        initPage(strings);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }
//
//
    private void initPage(List<String> keyWords) {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_list, R.id.tv_text, keyWords);
            listView.setAdapter(mAdapter);
            listView.setOnItemClickListener(itemClick());
        } else {
            mAdapter.clear();
            mAdapter.addAll(keyWords);
            mAdapter.notifyDataSetChanged();
        }
    }

    private AdapterView.OnItemClickListener itemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "搜索:" + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        };
    }

//
    /**
     * 模拟网路接口获取匹配到的关键字列表
     */
    private Observable<List<String>> getKeyWordFormNet(final String key) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                SystemClock.sleep(1000);
                //这里是网络请求操作...
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    datas.add("KeyWord:" + key + i);
                }
                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }
}

