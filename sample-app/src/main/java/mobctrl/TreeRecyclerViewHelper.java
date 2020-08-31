package mobctrl;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


import mobctrl.adapter.RecyclerAdapter;
import mobctrl.interfaces.OnScrollToListener;
import mobctrl.model.ItemData;
import rx.android.samples.R;

/**
 * Created by Lin on 16/9/19.
 */

public class TreeRecyclerViewHelper extends RecyclerViewHelper {

    public TreeRecyclerViewHelper(Context context, RecyclerView view) {
        this.context=context;
        this.recyclerView=view;
        init();
        readData();
    }

    private void readData() {
        InputStream in=context.getResources().openRawResource(R.raw.data);
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuffer sb=new StringBuffer();
        try {
            String result;
            while((result=br.readLine())!=null)
            {
               sb.append(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ItemData> list=
                new Gson().fromJson(sb.toString(),new TypeToken<List<ItemData>>(){}.getType());

        ((RecyclerAdapter) adapter).addAll(list, 0);



    }

    @Override
    public void init() {
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.getItemAnimator().setAddDuration(100);
        recyclerView.getItemAnimator().setRemoveDuration(100);
        recyclerView.getItemAnimator().setMoveDuration(200);
        recyclerView.getItemAnimator().setChangeDuration(100);


        adapter = new RecyclerAdapter(context);
        recyclerView.setAdapter(adapter);
        ((RecyclerAdapter) adapter).setOnScrollToListener(new OnScrollToListener() {

            @Override
            public void scrollTo(int position) {
                recyclerView.scrollToPosition(position);
            }
        });
    }







}
