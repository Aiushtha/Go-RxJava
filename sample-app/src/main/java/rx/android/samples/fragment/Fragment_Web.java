package rx.android.samples.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mobctrl.model.ItemData;

/**
 * Created by Lin on 2016/10/12.
 */

public class Fragment_Web extends Fragment {

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
        try {
            InputStream in = getContext().getAssets().open(data.link);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String result;
            while ((result = br.readLine()) != null) {
                sb.append(result);
                sb.append("<br>");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:code('" + sb.toString() + "'" + ")");
            }
        });
        webView.loadUrl("file:///android_asset/rtf/code_demo.html");
        return webView;
    }

}
