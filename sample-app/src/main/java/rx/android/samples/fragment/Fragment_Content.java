package rx.android.samples.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import mobctrl.model.ItemData;

/**
 * Created by Lin on 2016/10/12.
 */

public class Fragment_Content extends Fragment {

    private WebView webView;

    public static TextView textView;

    public static String content;
    private Intent intent;
    private StringBuffer sb=new StringBuffer();
    private ItemData data;


    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        webView=new WebView(getContext());
        webView.getSettings().setJavaScriptEnabled(true);
        data= (ItemData) getActivity().getIntent().getSerializableExtra("DATA");
        webView.loadUrl(data.explain);
        return webView;
    }

}
