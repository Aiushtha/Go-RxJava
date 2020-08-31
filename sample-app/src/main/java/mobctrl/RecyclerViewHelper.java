package mobctrl;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lin on 16/9/19.
 */

public abstract class RecyclerViewHelper {

    protected Context context;
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;

    public abstract void init();

//    public abstract void upData(List list);

}
