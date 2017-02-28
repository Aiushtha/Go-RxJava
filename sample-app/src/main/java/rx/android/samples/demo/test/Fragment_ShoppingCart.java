package rx.android.samples.demo.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import rx.android.samples.R;
import rx.android.samples.demo.BaseFragment;

/**
 * Created by Lin on 2017/2/20.
 */

public class Fragment_ShoppingCart extends BaseFragment {


    private ListView listView_shop;
    private ListView listView_food;

    private String[] data_shop=new String[]{
            "黄焖鸡","水饺店","面食店"
    };
    private String[][] data_food=new String[][]{
            {"黄焖鸡米饭","牛蛙饭","鲜虾面"},
            {"白菜馅","韭菜馅","鲜肉馅"},
            {"大肠面","爆鱼面",""}
    };

    private int index_shop;
    private ShopAdapter shop_adapter;
    private FoodAdapter food_adapter;
    private Button btn;


    public View onCreateView(LayoutInflater inflater, ViewGroup group,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.demo_shoppingcart,null);
        listView_shop= (ListView) view.findViewById(R.id.list1);
        listView_food= (ListView) view.findViewById(R.id.list2);
        btn=(Button)view.findViewById(R.id.btn);
        shop_adapter=new ShopAdapter();
        listView_shop.setAdapter(shop_adapter);
        listView_shop.setOnItemClickListener(shop_adapter);

        food_adapter=new FoodAdapter();
        listView_food.setAdapter(food_adapter);
        listView_food.setOnItemClickListener(food_adapter);
        return view;
    }


    public class ShopAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{


        @Override
        public int getCount() {
            return data_shop.length;
        }

        @Override
        public Object getItem(int position) {
            return data_shop[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView==null)
            {
                convertView=new TextView(getActivity());
            }
            textView= (TextView) convertView;
            textView.setText(data_shop[position]);
            return textView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            index_shop=position;
            food_adapter.notifyDataSetChanged();

        }
    }
    public class FoodAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

        @Override
        public int getCount() {
            return data_food[index_shop].length;
        }

        @Override
        public Object getItem(int position) {
            return data_food[index_shop][position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView==null)
            {
                convertView=new TextView(getActivity());
            }
            textView= (TextView) convertView;
            textView.setText(data_food[index_shop][position]);
            return textView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            btn.setText();
        }
    }




}
