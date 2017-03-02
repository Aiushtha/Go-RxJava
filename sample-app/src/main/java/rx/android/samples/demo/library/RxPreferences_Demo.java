package rx.android.samples.demo.library;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Func1;

/**
 * Created by Lin on 2017/2/27.
 */

public class RxPreferences_Demo extends BaseFragment {
    private CheckBox checkBox;
    private EditText editText;
    private Preference<String> pre_username;
    private Preference<Boolean> pre_sex;


    //https://github.com/f2prateek/rx-preferences


    @Override
    protected void init() {
        super.init();
        btn_submit.setText("查看变量");
        editText=new EditText(getActivity());
        checkBox=new CheckBox(getActivity());
        checkBox.setText("是否为男性");
        container.addView(checkBox);
        container.addView(editText);
        //创建实例
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);

        //创建个人
        pre_username = rxPreferences.getString("username");
        pre_sex= rxPreferences.getBoolean("sex", true);

        //绑定
        RxCompoundButton.checkedChanges(checkBox)
                .subscribe(pre_sex.asAction());

        RxTextView.textChanges(editText)
                .flatMap(new Func1<CharSequence, Observable<String>>() {
                    @Override
                    public Observable<String> call(final CharSequence charSequence) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext(charSequence.toString());
                                subscriber.onCompleted();
                            }
                        });
                    }
                })
            .subscribe(pre_username.asAction());
    }

    @Override
    public void runCode() {
        super.runCode();
        println("username:"+pre_username.get());
        println("sex:"+pre_sex.get());
    }
}
