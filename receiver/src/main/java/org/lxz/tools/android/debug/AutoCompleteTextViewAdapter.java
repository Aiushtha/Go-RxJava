package org.lxz.tools.android.debug;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin on 16/5/6.
 */
public class AutoCompleteTextViewAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public LayoutInflater inflater;
    public int layoutId=android.R.layout.simple_list_item_1;
    private Filter mFilter;
    private List<String> list=new ArrayList<String>();

    public AutoCompleteTextViewAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        list.add("123");
        list.add("123");
        list.add("123");


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(layoutId, null);
        TextView text1 = (TextView)convertView.findViewById(android.R.id.text1);
        text1.setText("1123");
        text1.setTextColor(Color.WHITE);
        return convertView;
    }

    /**
     * {@inheritDoc}
     */
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new Filter(){
                FilterResults fr;
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    if(fr==null)
                    {
                        fr=new FilterResults();
                        fr.values=list;
                        fr.count=list.size();
                    }
                    return fr;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                }
            };
        }
        return mFilter;
    }
}
