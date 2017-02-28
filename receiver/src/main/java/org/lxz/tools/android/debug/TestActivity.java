package org.lxz.tools.android.debug;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by Lin on 16/5/6.
 */
public class TestActivity extends Activity {
    private AutoCompleteTextView auto;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        auto= (AutoCompleteTextView) findViewById(R.id.auto);
        auto.setThreshold(-1);
//        final List<String> list = new ArrayList<String>();
//        list.add("123");
//        list.add("321");
//        list.add("aaa");
//        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(auto.getContext(),
//                android.R.layout.simple_dropdown_item_1line, list);
//
//
//        auto.setAdapter(arrAdapter);
//        arrAdapter.notifyDataSetChanged();
//        arrAdapter.notifyDataSetInvalidated();
//        Toast.makeText(auto.getContext(), "1111", Toast.LENGTH_LONG).show();
        final String [] arr={"aa","aab","aac"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        auto.setAdapter(arrayAdapter);
        auto.addTextChangedListener(new TextWatcher() {

            private ArrayAdapter<String> adapter;

            public void afterTextChanged(Editable editable) {

                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    adapter.notifyDataSetInvalidated();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

        });
        auto.post(new Runnable() {
            @Override
            public void run() {
                auto.showDropDown();
            }
        });

    }
}
