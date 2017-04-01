package com.example.tongmin.nestscrollingandrecyleview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ParentView page;
    private LinearLayoutManager layoutManager;
    private TextView page1;
    private TextView page2;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_scroll);
        setContentView(R.layout.content_main);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        List<View> viewList=new ArrayList<>();
        View view=getLayoutInflater().inflate(R.layout.fragment_page1,viewPager,false);
        ViewPager view_pager2=(ViewPager)view.findViewById(R.id.page1_viewPager);
        List<View> viewList1=new ArrayList<>();
        View frag3=getLayoutInflater().inflate(R.layout.fragment_page3,view_pager2,false);
        RecyclerView listView = (RecyclerView) frag3.findViewById(R.id.list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; ++i) {
            list.add(" String " + i);
        }
        listView.setAdapter(new StringAdapter(list));
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        viewList1.add(frag3);
        viewList.add(view);
        /**
         * 初始化第二个fragment
         */
        view=getLayoutInflater().inflate(R.layout.fragment_page2,viewPager,false);
        listView=(RecyclerView) view.findViewById(R.id.list);
        list=new ArrayList<>();
        for (int i = 30; i < 60; ++i) {
            list.add(" String " + i);
        }
        listView.setAdapter(new StringAdapter(list));
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        viewList.add(view);
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(viewList);
        viewPager.setAdapter(myPagerAdapter);
        myPagerAdapter=new MyPagerAdapter(viewList1);
        view_pager2.setAdapter(myPagerAdapter);
        /**
         * 头部布局
         */
        page=(ParentView)findViewById(R.id.page);
        View page_header=getLayoutInflater().inflate(R.layout.fragment_header_view,page,false);
        page1=(TextView)page_header.findViewById(R.id.page1);
        page2=(TextView)page_header.findViewById(R.id.page2);
        page.setHeaderView(page_header);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page1.setTextColor(getResources().getColor(R.color.tab_selected));
                page2.setTextColor(getResources().getColor(R.color.tab_unselected));
                page1.setBackgroundResource(R.drawable.tab_selected);
                page2.setBackgroundResource(R.drawable.tab_unselected);
                viewPager.setCurrentItem(0);
            }
        });
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page1.setTextColor(getResources().getColor(R.color.tab_unselected));
                page2.setTextColor(getResources().getColor(R.color.tab_selected));
                page1.setBackgroundResource(R.drawable.tab_unselected);
                page2.setBackgroundResource(R.drawable.tab_selected);
                viewPager.setCurrentItem(1);

            }
        });
    }

    class StringAdapter extends RecyclerView.Adapter<ViewHolder> {


        private List<String> list;
        private LayoutInflater inflater;

        StringAdapter(List<String> list) {
            this.list = list;
            inflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item, null, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    class MyPagerAdapter extends PagerAdapter{
        List<View>viewList;
        public MyPagerAdapter(List viewList){
            this.viewList=viewList;
        }
        @Override
        public int getCount() {
            return viewList==null ? 0 : viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }

}
