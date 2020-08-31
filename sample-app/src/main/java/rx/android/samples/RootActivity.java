package rx.android.samples;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import mobctrl.TreeRecyclerViewHelper;

/**
 * Created by Lin on 2016/10/10.
 */

public class RootActivity extends Activity {

    private RecyclerView recyclerView;
    private TreeRecyclerViewHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        helper=new TreeRecyclerViewHelper(this,recyclerView);




    }
}
