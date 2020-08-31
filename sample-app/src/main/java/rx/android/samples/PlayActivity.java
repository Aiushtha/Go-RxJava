package rx.android.samples;

import android.os.Bundle;
import android.util.TypedValue;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import mobctrl.model.ItemData;
import rx.android.samples.fragment.CodeFragmentManager;
import rx.android.samples.view.PagerSlidingTabStrip;


/**
 * Created by Lin on 2016/10/12.
 */

public class PlayActivity extends FragmentActivity {

    PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ItemData itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        itemData= (ItemData) getIntent().getSerializableExtra("DATA");

        adapter = new MyPagerAdapter(getSupportFragmentManager());



        tabs.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pager.setAdapter(adapter);
                tabs.setTabPaddingLeftRight(18);
                pager.setOffscreenPageLimit(9);


                final int pageMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources()
                                .getDisplayMetrics());
                pager.setPageMargin(pageMargin);
                tabs.setViewPager(pager);
            }
        });


    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public String[] strs = {"代码示例", "查看结果", "分析介绍"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strs[position];
        }

        @Override
        public int getCount() {
            return strs.length;
        }

        @Override
        public Fragment getItem(int position) {
            return CodeFragmentManager.newInstance(position,itemData);
        }

    }
}
