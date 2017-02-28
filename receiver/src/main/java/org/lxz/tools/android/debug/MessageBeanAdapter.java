/*
 * ******************************************************************************
 *   Copyright (c) 2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */
package org.lxz.tools.android.debug;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class MessageBeanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public HashMap<String, Integer> colorMap = new HashMap<>();

    {
        colorMap.put(MessageBean.LEVEL_VERBOSE, Color.parseColor("#336666"));
        colorMap.put(MessageBean.LEVEL_DEBUG, Color.parseColor("#600030"));
        colorMap.put(MessageBean.LEVEL_INFO, Color.parseColor("#000079"));
        colorMap.put(MessageBean.LEVEL_WARN, Color.parseColor("#796400"));
        colorMap.put(MessageBean.LEVEL_ERROR, Color.parseColor("#930000"));
        colorMap.put(MessageBean.LEVEL_ASSERT, Color.parseColor("#006000"));
    }


    private final Context mContext;
    private List<MessageBean> mItems=new ArrayList<>();
    private int mCurrentItemId = 0;
    private int layoutId=R.layout.adapter_message_list_item;



    public MessageBeanAdapter(Context context) {
        mContext = context;
    }

    public  class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_subject;
        public TextView tv_id;
        public TextView tv_type;
        public TextView tv_tag;
        public TextView tv_level;
        public TextView tv_src;
        public TextView tv_time;
        public View convertView;
        public SimpleViewHolder(View view) {
            super(view);
            tv_subject = (TextView) view.findViewById(R.id.tv_subject);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_tag = (TextView) view.findViewById(R.id.tv_tag);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_src = (TextView) view.findViewById(R.id.tv_src);
            tv_level= (TextView) view.findViewById(R.id.tv_level);
            if(layoutId==R.layout.adapter_message_list_item) {
                tv_time = (TextView) view.findViewById(R.id.tv_time);
                convertView = view;
            }else if(layoutId==R.layout.adapter_message_grid_item)
            {
                convertView = view;
            }
            convertView.setOnClickListener(this);
        }
        int position;
        MessageBean bean;
        public void setData(int position,MessageBean bean)
        {
          this.position=position;this.bean=bean;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), WebActivity.class);
            intent.putExtra("data", MessageCache.getList().get(position));
            v.getContext().startActivity(intent);
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        return new SimpleViewHolder(view);
    }

    public SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageBean bean=mItems.get(position);
        ((SimpleViewHolder) holder).setData(position, bean);
        ((SimpleViewHolder) holder).tv_id.setText("");
        ((SimpleViewHolder) holder).tv_subject.setText("");
        ((SimpleViewHolder) holder).tv_tag.setText("");
        ((SimpleViewHolder) holder).tv_type.setText("");
        ((SimpleViewHolder) holder).tv_src.setText("");
        ((SimpleViewHolder) holder).tv_level.setText("");
        ((SimpleViewHolder) holder).convertView.setBackgroundColor(colorMap.get(bean.getLevel()));
        if (layoutId==R.layout.adapter_message_list_item) {
            ((SimpleViewHolder) holder).tv_id.setText(bean.getId());
            ((SimpleViewHolder) holder).tv_level.setText(bean.getLevel());
            ((SimpleViewHolder) holder).tv_src.setText(bean.getSrc());
            ((SimpleViewHolder) holder).tv_subject.setText(bean.getSubject());
            ((SimpleViewHolder) holder).tv_tag.setText(bean.getTag());
            ((SimpleViewHolder) holder).tv_type.setText(bean.getType());
            ((SimpleViewHolder) holder).tv_time.setText(sdft.format(bean.createTime));
            ((SimpleViewHolder) holder).convertView.setBackgroundColor(colorMap.get(bean.getLevel()));
        }else if(layoutId==R.layout.adapter_message_grid_item)
        {
            ((SimpleViewHolder) holder).tv_id.setText(bean.getId());
            ((SimpleViewHolder) holder).tv_subject.setText(bean.getSubject());
            ((SimpleViewHolder) holder).tv_tag.setText(bean.getTag());
            ((SimpleViewHolder) holder).tv_type.setText(bean.getType());
            ((SimpleViewHolder) holder).tv_src.setText(bean.getSrc());
            ((SimpleViewHolder) holder).tv_level.setText(bean.getLevel());
            ((SimpleViewHolder) holder).convertView.setBackgroundColor(colorMap.get(bean.getLevel()));
        }
    }


//    public void addItem(int position) {
//        final int id = mCurrentItemId++;
//        mItems.add(position, id);
//        notifyItemInserted(position);
//    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        mItems=MessageCache.getList();
        return mItems.size();
    }

    public int getLayoutId() {
        return layoutId;
    }

    public MessageBeanAdapter setLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public List<MessageBean> getItems() {
        return mItems;
    }

    public MessageBeanAdapter setItems(List<MessageBean> mItems) {
        this.mItems = mItems;
        return this;
    }


}
