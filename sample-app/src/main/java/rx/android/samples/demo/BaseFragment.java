package rx.android.samples.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import rx.android.samples.R;


/**
 * Created by Lin on 16/10/9.
 */

public class BaseFragment extends RxV4Fragment {


    public TextView textView;
    public Button btn_submit;
    public Button btn_clear;
    public LinearLayout container;
    public LinearLayout contentView;
    public ScrollView scrollView;

    public void println(Object o) {
        if(o!=null) {
            println(o.toString());
        }else
        {
            println("null");
        }
    }

    public void println(final String s) {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.append(s + "\n");
                    }
                });

    }


    public void runCode(){
    }
    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test,null);
        textView= (TextView) view.findViewById(R.id.text);
        btn_submit= (Button) view.findViewById(R.id.btn_submit);
        container= (LinearLayout) view.findViewById(R.id.container);
        contentView= (LinearLayout) view.findViewById(R.id.contentView);
        scrollView=(ScrollView)view.findViewById(R.id.scrollView);
        btn_clear=(Button)view.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runCode();
            }
        });
        init();
        return view;
    }

    protected void init() {

    }

}
